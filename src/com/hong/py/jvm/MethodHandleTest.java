package com.hong.py.jvm;


import java.lang.invoke.MethodHandle;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    class GrandFather {
      void thinking() {
         System.out.println("i am grandFather");
      }
    }

    class Father extends GrandFather {
      void thinking() {
         System.out.println("i am father");
      }
    }


    class Son extends Father {
      void thinking() {

          MethodType methodType = MethodType.methodType(void.class);
          try {
              MethodHandle mh = lookup().findSpecial(GrandFather.class, "thinking", methodType, getClass());
              mh.invoke(this);
          } catch (NoSuchMethodException e) {
              e.printStackTrace();
          } catch (IllegalAccessException e) {
              e.printStackTrace();
          } catch (Throwable throwable) {
              throwable.printStackTrace();
          }

      }
    }

    public static void main(String[] args) {
        (new MethodHandleTest().new Son()).thinking();
    }

}
