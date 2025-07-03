

package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext student=new ClassPathXmlApplicationContext("../resources/resources.xml");
        Student s= (Student) student.getBean("st");
        System.out.println( "Hello World!" );
        SpringBoot b= (SpringBoot) student.getBean("fun");
        b.hello();

    }
}
