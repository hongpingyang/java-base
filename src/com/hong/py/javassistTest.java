package com.hong.py;

import javassist.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/23 16:44
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/23 16:44
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class javassistTest {

    public static void main(String[] args) throws Exception {
        //testAop();
        testCodeTextGenerator();
    }

    public static void testAop() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
        //实现AOP
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.hong.py.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        //使用方法中的参数$0代表的是this，$1代表的是第一个参数，$2表示第二个参数。
        m.insertBefore("{ System.out.println(\"Hello.say():\"+$1); }");

        CtConstructor[] ctConstructor=cc.getConstructors();
        ctConstructor[0].insertBefore("{System.out.println(\"Hello.say.Constructor\");}");
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say("洪大洋");
    }

    /**
     * 测试文本生成类
     */
    public static void testCodeTextGenerator() {

        String codeText="package com.alibaba.dubbo.rpc;\n" +
                "import com.alibaba.dubbo.common.extension.ExtensionLoader;\n" +
                "public class Protocol$Adaptive implements com.alibaba.dubbo.rpc.Protocol {\n" +
                "public void destroy() {throw new UnsupportedOperationException(\"method public abstract void com.alibaba.dubbo.rpc.Protocol.destroy() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!\");\n" +
                "}\n" +
                "public int getDefaultPort() {throw new UnsupportedOperationException(\"method public abstract int com.alibaba.dubbo.rpc.Protocol.getDefaultPort() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!\");\n" +
                "}\n" +
                "public com.alibaba.dubbo.rpc.Exporter export(com.alibaba.dubbo.rpc.Invoker arg0) throws com.alibaba.dubbo.rpc.RpcException {\n" +
                "if (arg0 == null) throw new IllegalArgumentException(\"com.alibaba.dubbo.rpc.Invoker argument == null\");\n" +
                "if (arg0.getUrl() == null) throw new IllegalArgumentException(\"com.alibaba.dubbo.rpc.Invoker argument getUrl() == null\");com.alibaba.dubbo.common.URL url = arg0.getUrl();\n" +
                "String extName = ( url.getProtocol() == null ? \"dubbo\" : url.getProtocol() );\n" +
                "if(extName == null) throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(\" + url.toString() + \") use keys([protocol])\");\n" +
                "com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);\n" +
                "return extension.export(arg0);\n" +
                "}\n" +
                "public com.alibaba.dubbo.rpc.Invoker refer(java.lang.Class arg0, com.alibaba.dubbo.common.URL arg1) throws com.alibaba.dubbo.rpc.RpcException {\n" +
                "if (arg1 == null) throw new IllegalArgumentException(\"url == null\");\n" +
                "com.alibaba.dubbo.common.URL url = arg1;\n" +
                "String extName = ( url.getProtocol() == null ? \"dubbo\" : url.getProtocol() );\n" +
                "if(extName == null) throw new IllegalStateException(\"Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(\" + url.toString() + \") use keys([protocol])\");\n" +
                "com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);\n" +
                "return extension.refer(arg0, arg1);\n" +
                "}\n" +
                "}";

        javassistTest test = new javassistTest();
        try {
            test.doCompile("com.alibaba.dubbo.rpc.Protocol$Adaptive",codeText);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s+([\\w\\.\\*]+);\n");

    private static final Pattern EXTENDS_PATTERN = Pattern.compile("\\s+extends\\s+([\\w\\.]+)[^\\{]*\\{\n");

    private static final Pattern IMPLEMENTS_PATTERN = Pattern.compile("\\s+implements\\s+([\\w\\.]+)\\s*\\{\n");

    private static final Pattern METHODS_PATTERN = Pattern.compile("\n(private|public|protected)\\s+");

    private static final Pattern FIELD_PATTERN = Pattern.compile("[^\n]+=[^\n]+;");

    public  Class<?> doCompile(String name, String source) throws Throwable {

        int i = name.lastIndexOf('.');
        String className = i < 0 ? name : name.substring(i + 1);
        ClassPool pool = new ClassPool(true);
        pool.appendClassPath(new LoaderClassPath(getClass().getClassLoader()));
        Matcher matcher = IMPORT_PATTERN.matcher(source);
        List<String> importPackages = new ArrayList<String>();
        Map<String, String> fullNames = new HashMap<String, String>();
        while (matcher.find()) {
            String pkg = matcher.group(1);
            if (pkg.endsWith(".*")) {
                String pkgName = pkg.substring(0, pkg.length() - 2);
                pool.importPackage(pkgName);
                importPackages.add(pkgName);
            } else {
                int pi = pkg.lastIndexOf('.');
                if (pi > 0) {
                    String pkgName = pkg.substring(0, pi);
                    pool.importPackage(pkgName);
                    importPackages.add(pkgName);
                    fullNames.put(pkg.substring(pi + 1), pkg);
                }
            }
        }
        String[] packages = importPackages.toArray(new String[0]);
        matcher = EXTENDS_PATTERN.matcher(source);
        CtClass cls;
        if (matcher.find()) {
            String extend = matcher.group(1).trim();
            String extendClass;
            if (extend.contains(".")) {
                extendClass = extend;
            } else if (fullNames.containsKey(extend)) {
                extendClass = fullNames.get(extend);
            } else {
                extendClass = packages[0]+"."+extend;//      ClassUtils.forName(packages, extend).getName();
            }
            cls = pool.makeClass(name, pool.get(extendClass));
        } else {
            cls = pool.makeClass(name);
        }
        matcher = IMPLEMENTS_PATTERN.matcher(source);
        if (matcher.find()) {
            String[] ifaces = matcher.group(1).trim().split("\\,");
            for (String iface : ifaces) {
                iface = iface.trim();
                String ifaceClass;
                if (iface.contains(".")) {
                    ifaceClass = iface;
                } else if (fullNames.containsKey(iface)) {
                    ifaceClass = fullNames.get(iface);
                } else {
                    ifaceClass = packages[0]+"."+iface;//   ClassUtils.forName(packages, iface).getName();
                }
                //cls.addInterface(pool.get(ifaceClass));
            }
        }
        String body = source.substring(source.indexOf("{") + 1, source.length() - 1);
        String[] methods = METHODS_PATTERN.split(body);
        for (String method : methods) {
            method = method.trim();
            if (method.length() > 0) {
                if (method.startsWith(className)) {
                    cls.addConstructor(CtNewConstructor.make("public " + method, cls));
                } else if (FIELD_PATTERN.matcher(method).matches()) {
                    cls.addField(CtField.make("private " + method, cls));
                } else {
                    cls.addMethod(CtNewMethod.make("public " + method, cls));
                }
            }
        }
        return cls.toClass(getClass().getClassLoader(), javassistTest.class.getProtectionDomain());
    }



}

