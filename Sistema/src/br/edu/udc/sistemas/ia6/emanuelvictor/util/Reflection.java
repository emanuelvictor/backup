package br.edu.udc.sistemas.ia6.emanuelvictor.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Vector;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Transient;

public class Reflection {

	private static void validate(Class<?> c) throws Exception {
		if (!c.isAnnotationPresent(Entity.class)) {
			throw new Exception("Annotation " + Entity.class + " is not present");
		}
		if (!c.isAnnotationPresent(Table.class)) {
			throw new Exception("Annotation " + Table.class + " is not present");
		}
	}

	public static Table getTable(Class<?> c) throws Exception {
		validate(c);
		return (Table) c.getAnnotation(Table.class);
	}

	public static Field getIdField(Class<?> c) throws Exception {
		validate(c);
		Field fieldList[] = c.getDeclaredFields();
		for (int i = 0; i < fieldList.length; i++) {
			Field field = fieldList[i];
			if ((field.isAnnotationPresent(Id.class)) && (field.isAnnotationPresent(Column.class))) {
				return field;
			}
		}
		throw new Exception();
	}

	public static Field[] getFields(Class<?> c, boolean bTransient) throws Exception {
		validate(c);
		Field fieldList[] = c.getDeclaredFields();
		Collection<Field> result = new Vector<Field>();
		for (int i = 0; i < fieldList.length; i++) {
			Field field = fieldList[i];
			if ((field.isAnnotationPresent(Column.class)) || ((bTransient) && field.isAnnotationPresent(Transient.class))) {
				result.add(field);
			}
		}
		return (Field[]) result.toArray(new Field[result.size()]);
	}

	public static Object getFieldValue(Field field, Object obj)throws Exception {
		Method methodList[] = obj.getClass().getDeclaredMethods();
		for (int i = 0; i < methodList.length; i++) {
			if (methodList[i].getName().toUpperCase().equals(("GET" + field.getName().toUpperCase()))) {
				return methodList[i].invoke(obj, new Object[0]);
			}
		}
		throw new Exception();
	}

	public static void setFieldValue(Field field, Object obj, Object values)throws Exception {
		Method methodList[] = obj.getClass().getDeclaredMethods();
		for (int i = 0; i < methodList.length; i++) {
			if (methodList[i].getName().toUpperCase().equals(("SET" + field.getName().toUpperCase()))) {
				methodList[i].invoke(obj, values);
			} else if (i == methodList.length) {
				throw new Exception();
			}
		}
	}

	public static Object getIdFieldValue(Object obj) throws Exception {
		return getFieldValue(getIdField(obj.getClass()), obj);
	}

	public static void setIdFieldValue(Object obj, Object value)
			throws Exception {
		setFieldValue(getIdField(obj.getClass()), obj, value);
	}

}
