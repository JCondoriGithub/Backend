package testannotazioni;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Scanner;

public class Main {

	public static void elabora(Class c) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		// si crea una istanza di c
		Object obj = c.getConstructor().newInstance();
		// per ogni metodo set<X>(String) si chiede il dato all'utente e lo si imposta
		Scanner in = new Scanner(System.in);
		for (Method m : c.getMethods())
			if (m.getName().startsWith("set") && m.getReturnType()==Void.TYPE &&
			m.getParameterCount()==1 && m.getParameters()[0].getType()==String.class)
			{
				Msg msg = m.getAnnotation(Msg.class);
				String prompt = (msg!=null?msg.value():m.getName().substring(3));
				System.out.print(prompt + ":");
				m.invoke(obj, in.next());
			}
		in.close();
		// si chiamano tutti i metodi annotati come @PostInit
		for (Method m:c.getMethods())
			if (m.isAnnotationPresent(PostInit.class))
				m.invoke(obj);
		// si stampa a video l'oggetto ottenuto
		System.out.println(obj);
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		elabora(User.class);
		/*
		 * Class c = Class.forName("testannotazioni.User"); MiaAnnotazione ann =
		 * (MiaAnnotazione)c.getAnnotation(MiaAnnotazione.class); if (ann!=null)
		 * System.out.println("annotato con msg=" + ann.msg()); Constructor co =
		 * c.getConstructor(String.class, String.class); Object obj =
		 * co.newInstance("Mario", "Rossi"); System.out.println("ho creato un " +
		 * obj.getClass()); System.out.println(obj); for (Method m : c.getMethods()) if
		 * (m.getReturnType() == String.class && m.getParameterCount() == 0) { String
		 * res = (String) m.invoke(obj); System.out.println(m.getName() + " --> " +
		 * res); }
		 */
	}

}
