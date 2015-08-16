package br.edu.ftlf.ads.pbd.bean.usertype;
 
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
 
public class EnumSetUserType<E extends Enum<E>> implements UserType {
    @SuppressWarnings("rawtypes")
    private Class<? extends EnumSet> type;
    private Class<E> enumType;
    
    public EnumSetUserType(Class<E> enumType) {
        this.type = EnumSet.noneOf(enumType).getClass();
        this.enumType = enumType;
    }
    
    @SuppressWarnings("unchecked")
	public EnumSetUserType() {
    	Type genericType = ((Class<?>) getClass()).getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) genericType;
    	this.enumType = (Class<E>) parameterizedType.getActualTypeArguments()[0];
    	this.type = EnumSet.noneOf(enumType).getClass();
    }
 
    private static final int[] SQL_TYPES = {Types.VARCHAR};
    public int[] sqlTypes() {
        return SQL_TYPES;
    }
 
    @SuppressWarnings("rawtypes")
    public Class returnedClass() {
        return type;
    }
 
    public Object nullSafeGet(ResultSet resultSet, String[] names,
            SessionImplementor sessionImplementor, Object owner) throws HibernateException, SQLException {
        String name = resultSet.getString(names[0]);
        EnumSet<E> result = EnumSet.noneOf(enumType);
        if (!resultSet.wasNull() && !name.isEmpty()) {
            String[] values = name.split(",");
            List<E> enumList = new ArrayList<E>();
            for(String value : values) {
                enumList.add(Enum.valueOf(enumType, value));
            }
            result = EnumSet.copyOf(enumList);
        }
        return result;
    }
 
    @SuppressWarnings("unchecked")
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index,
            SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if (null == value) {
            preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            Set<E> values = (Set<E>)value;
            String sqlValue = "";
            if (!values.isEmpty()) {
                StringBuffer buf = new StringBuffer();
                for(E val : values) {
                    buf.append(val.name()).append(",");
                }
                sqlValue = buf.substring(0, buf.length() - 1);
            }
            preparedStatement.setString(index, sqlValue);
        }
    }
 
    public Object deepCopy(Object value) throws HibernateException{
        return value;
    }
 
    public boolean isMutable() {
        return false;
    }
 
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }
 
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable)value;
    }
 
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y)
            return true;
        if (null == x || null == y)
            return false;
        return x.equals(y);
    }
    
    @Override
    public String toString() {
    	return EnumSet.allOf(enumType).toString();
    }
}

