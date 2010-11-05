package mat7510.playground.reflection;

import java.lang.reflect.Constructor;
import java.util.List;

public class InstantiationUsingReflection {


	public static void main(String args[])	{

		try {
			Class<?> cls = Class.forName("java.util.ArrayList");
			Constructor<?> ct = cls.getConstructor();
			List reflectiveList = (List)ct.newInstance();

			reflectiveList.add("lalala");
			reflectiveList.add("lululu");
			for (Object object : reflectiveList) {
				System.out.println(object);
			}
		}
		catch (Throwable e) {
			System.err.println(e);
		}
	}
}

