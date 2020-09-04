package com.hong.py;

import com.hong.py.pojo.Person;
import com.hong.py.pojo.Student;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * author: hongpy
 * create time: 2020-06-09 10:24
 * description:
 * life for code
 */
public class ReflectDemo {

    private static Student student;
    private static List<Student> studentList;
    private static Student[] students;
    public static void main(String[] args) throws NoSuchMethodException {
        student = new Student();
        studentList = Arrays.asList(student);
        students = new Student[]{student};
        construct();
    }

    public static void construct() throws NoSuchMethodException {
        Class<Student> studentClass = Student.class;
        //获取类内所有public修饰的构造方法
        Constructor<?>[] constructors = studentClass.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName()+":"+constructor.getParameterCount());
        }
        System.out.println("=========getDeclaredConstructors==========");
        //获取类内所有构造方法(包含私有)
        Constructor<?>[] constructors1 = studentClass.getDeclaredConstructors();
        for (Constructor constructor : constructors1) {
            System.out.println(constructor.getName()+":"+constructor.getParameterCount());
        }

        System.out.println("=========getMethods==========");
        //获取类内所有public修饰的成员方法，包括从父类继承而来的方法
        Method[] methods = studentClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName()+":"+method.getParameterCount());
        }
        System.out.println("=========getDeclaredMethods==========");
        //获取类内所有成员方法(包含私有)，但不包括从父类继承而来的方法
        Method[] declaredMethods = studentClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName()+":"+method.getParameterCount());
        }
        System.out.println("=========getEnclosingMethod==========");
        System.out.println(student.classWithAnonymousClass().getClass().getEnclosingMethod());

        System.out.println("=========getFields==========");
        //获取内所有public修饰的成员变量，包括从父类继承而来的变量，
        Field[] fields = studentClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName()+":"+field.getType());
        }
        System.out.println("========getDeclaredFields===========");
        //获取类内所有成员变量(包含私有)，但不包括从父类继承而来的变量
        Field[] declaredFields = studentClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName()+":"+field.getType());
        }
        System.out.println("=======isAssignableFrom============");
        System.out.println(Person.class.isAssignableFrom(studentClass));

        System.out.println("========getGenericInterfaces===========");
        //主要是 获取由此对象表示的类或接口直接实现的接口的Type。
        //区别在于getGenericInterfaces可以返回其参数化类型
        Type[] genericInterfaces = studentClass.getGenericInterfaces();
        for (Type type : genericInterfaces) {
            System.out.println(type.getTypeName()+":"+type.getClass());
        }

        System.out.println("========getInterfaces===========");
        //主要是 获取由此对象表示的类或接口实现的接口
        Class<?>[] interfaces = studentClass.getInterfaces();
        for (Class clazz : interfaces) {
            System.out.println(clazz.getTypeName()+":"+clazz.getClass());
        }

        System.out.println("========getName===========");
        String name = studentClass.getName(); //com.hong.py.pojo.Student
        System.out.println(name);
        String simpleName = studentClass.getSimpleName(); //Student
        System.out.println(simpleName);
        String typeName = studentClass.getTypeName(); //com.hong.py.pojo.Student
        System.out.println(typeName);
        String canonicalName = studentClass.getCanonicalName(); //com.hong.py.pojo.Student
        System.out.println(canonicalName);
        System.out.println("========getName===list========");
        String listname = studentList.getClass().getName(); //java.util.Arrays$ArrayList
        System.out.println(listname);
        String listsimpleName = studentList.getClass().getSimpleName(); //ArrayList
        System.out.println(listsimpleName);
        String listtypeName = studentList.getClass().getTypeName(); //java.util.Arrays$ArrayList
        System.out.println(listtypeName);
        String listcanonicalName = studentList.getClass().getCanonicalName(); //java.util.Arrays.ArrayList
        System.out.println(listcanonicalName);
        System.out.println("========getName===array========");
        Class<?> c1 = students.getClass().getComponentType();
        System.out.println(c1);
        String arrayname = students.getClass().getName();  //[Lcom.hong.py.pojo.Student;
        System.out.println(arrayname);
        String arraysimpleName = students.getClass().getSimpleName(); //Student[]
        System.out.println(arraysimpleName);
        String arraytypeName = students.getClass().getTypeName(); //com.hong.py.pojo.Student[]
        System.out.println(arraytypeName);
        String arraycanonicalName = students.getClass().getCanonicalName();//com.hong.py.pojo.Student[]
        System.out.println(arraycanonicalName);
        //数组中getName()通过[L表示数组，getCanonicalName()通过在定义的类型后面加上[]表示数组。
        // 而getSimpleName()只是去掉getCanonicalName()返回结果前面的包部分。
        // 因此他们两个很相似，就像父子关系一样，getCanonicalName返回为null，getSimpleName就是空字符串。


        System.out.println("========typeParameters===========");
        TypeVariable<Class<Student>>[] typeParameters = studentClass.getTypeParameters();
        for (TypeVariable type : typeParameters) {
            System.out.println(type.getTypeName()+":"+type.getClass());
        }
        System.out.println("========getSuperclass===========");
        Class<? super Student> superclass = studentClass.getSuperclass();
        System.out.println(superclass);

        System.out.println("========getGenericSuperclass===========");
        Type genericSuperclass = studentClass.getGenericSuperclass();
        System.out.println(genericSuperclass.getTypeName()+":"+genericSuperclass.getClass());

        System.out.println("========getDeclaringClass===========");
        Class<?> declaringClass = studentClass.getDeclaringClass();
        System.out.println(declaringClass);
        Method getName = studentClass.getMethod("getName");
        Class<?> declaringClass1 = getName.getDeclaringClass();
        System.out.println(declaringClass1);

        //下界(就是下面的界限是确定的)   不影响往里存，但往外取只能放在Object对象里。可能是Student或Student的父类
        List<? super Student> studentlist=studentList;
        studentlist.add(new Student());

        //上界(就是上面的界限是确定的) <? extends T>不能往里存，只能往外取 ,可能是Person或Person的子类
        List<? extends Person> studentlist1=studentList;


    }

}
