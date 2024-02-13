package at.spengergasse.valedyn;

import at.spengergasse.valedyn.Exceptions.MissingAnnotationException;
import at.spengergasse.valedyn.Exceptions.NoFieldException;
import at.spengergasse.valedyn.annotations.AllColumns;
import at.spengergasse.valedyn.annotations.Table;
import at.spengergasse.valedyn.model.Entity;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SQLConverter<T extends Entity> {
    private String tableName;
    private Field[] fields;
    Class clazz;
    public SQLConverter(T obj) throws MissingAnnotationException, NoFieldException {
        this.clazz = obj.getClass();
        Annotation[] annotations = clazz.getAnnotations();

        boolean allColumns = false;
        boolean hasTableAnnotation = false;


        for(Annotation annotation : annotations){
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if(!allColumns && annotationType.equals(AllColumns.class)){
                allColumns = true;
            }

            if(!hasTableAnnotation && annotationType.equals(Table.class)){
                hasTableAnnotation = true;
            }

            if(allColumns && hasTableAnnotation){
                break;
            }
        }
        // TODO: find out  how to access private fields....
        if(!clazz.isAnnotationPresent(Table.class)){
            throw new MissingAnnotationException(String.format("ERR: No table annotation is available for class: %s", clazz.getName()));
        }
        hasTableAnnotation = true;

        if(clazz.isAnnotationPresent(AllColumns.class)){
            allColumns = true;
        }

        this.fields = clazz.getFields();
        // TODO: exception for when there aren't any column annotations
        if(this.fields.length == 0){
            throw new NoFieldException(String.format("ERR: No available Fields for class: %s", clazz.getName()));
        }
    }

    public String attributeTransformation(Field field) throws IllegalAccessException {
        System.out.println(field.get(clazz).toString());
        return "";
    }

    public void createTable(){

    }



}
