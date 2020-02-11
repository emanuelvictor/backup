package br.edu.udc.sistemas.ia6.emanuelvictor.factory;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Transient;
import br.edu.udc.sistemas.ia6.emanuelvictor.util.Reflection;

public class FactoryEntity {
	private static Object getFormatedValue(Field field, String rawValue) {
		Column column = field.getAnnotation(Column.class);
		Transient transientColumn = field.getAnnotation(Transient.class);
		int type;
		if (column != null) {
			type = column.type();
		} else {
			type = transientColumn.type();
		}
		if (rawValue != null) {
			switch (type) {
			case Column.CHAR:
				if (rawValue.length() > 0) {
					return rawValue.charAt(0);
				}
				break;
			case Column.STRING:
				return rawValue;
			case Column.DATE:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case Column.TIME:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case Column.DATETIME:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case Column.BOOLEAN:
				return rawValue.equals("true") || (!rawValue.equals("0") && (!rawValue.equals("false"))); 
			case Column.INTEGER:
				try {
					return Integer.parseInt(rawValue);
				} catch (Exception e) {
					return null;
				}
			case Column.LONG:
				try {
					return Long.parseLong(rawValue);
				} catch (Exception e) {
					return null;
				}
			case Column.FLOAT:
				try {
					return Float.parseFloat(rawValue);
				} catch (Exception e) {
					return null;
				}
			case Column.OBJECT:
				try {
					Integer id = Integer.parseInt(rawValue);
					Object objFK = field.getType().newInstance();
					Reflection.setIdFieldValue(objFK, id);
					return objFK;				
				} catch (Exception e) {
					return null;
				}
			default:
				break;
			}
		}
		return null;
	}
	
	
	
	public static Object createById(Integer id, Class <?> c) throws Exception {
		Object obj = c.newInstance();
		Reflection.setIdFieldValue(obj, id);
		return obj;
	}
	
	public static Object createByPost(HashMap <String,String> postData,  Class<?> c) throws Exception {
		Object obj = c.newInstance();
		Field fields[] = Reflection.getFields(c,true);
		for (Iterator<?> iterator = postData.keySet().iterator(); iterator.hasNext();) {
			String postFieldName = (String) iterator.next();
			for (int i = 0; i < fields.length; i++) {
				if (postFieldName.equalsIgnoreCase(fields[i].getName())) {
					Reflection.setFieldValue(fields[i], obj, getFormatedValue(fields[i], postData.get(postFieldName)));
					break;
				}
			}
		}
		return obj;
	}

	public static Object[] createByResultSet(ResultSet rst, Class<?> c)
			throws Exception {
		Vector<Object> result = new Vector<Object>();
		Field fields[] = Reflection.getFields(c, false);
		while (rst.next()) {
			Object obj = c.newInstance();
			for (int i = 0; i < fields.length; i++) {
				Column column = fields[i].getAnnotation(Column.class);
				switch (column.type()) {
				case Column.CHAR:
					Reflection.setFieldValue(fields[i], obj,
							rst.getString(column.name()));
					break;
				case Column.STRING:
					Reflection.setFieldValue(fields[i], obj,
							rst.getString(column.name()));
					break;
				case Column.DATE:
					Reflection.setFieldValue(fields[i], obj,
							rst.getDate(column.name()));
					break;
				case Column.TIME:
					Reflection.setFieldValue(fields[i], obj,
							rst.getTime(column.name()));
					break;
				case Column.DATETIME:
					Reflection.setFieldValue(fields[i], obj,
							rst.getDate(column.name()));
					break;
				case Column.BOOLEAN:
					Reflection.setFieldValue(fields[i], obj,
							rst.getBoolean(column.name()));
					break;
				case Column.INTEGER:
					Reflection.setFieldValue(fields[i], obj,
							rst.getInt(column.name()));
					break;
				case Column.LONG:
					Reflection.setFieldValue(fields[i], obj,
							rst.getLong(column.name()));
					break;
				case Column.FLOAT:
					Reflection.setFieldValue(fields[i], obj,
							rst.getFloat(column.name()));
					break;
				case Column.OBJECT:
					Object objFK = fields[i].getType().newInstance();
					Field idField = Reflection.getIdField(objFK.getClass());
					Reflection.setFieldValue(idField, objFK,rst.getInt(column.name()));
					Reflection.setFieldValue(fields[i], obj, objFK);
					break;
				default:
					break;
				}
			}
			result.add(obj);
		}
		return result.toArray(new Object[result.size()]);
	}
}
