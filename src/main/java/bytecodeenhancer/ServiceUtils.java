package bytecodeenhancer;

import javassist.*;

/**
 * 步骤3：编写增强的工具类，之后直接调用后再使用原来的业务类方法就实现增强了
 * ClassPool其实是一张保存了CtClass信息的哈希表，key=类的全限定类名，value=类名对应的CtClass对象。
 *          当需要对某个类修改的时候，通过方法getCtClass(className)从classpool获取到相应的CtClass
 * CtClass：编译时的类信息，一个class文件在代码中的抽象表现形式，全限定类名可以获取CtClass对象，用于表示这个类文件
 * CtMethod: 类中的方法 定义或修改
 * CtField:  类中的属性 定义或修改
 *
 * javassist 增强代码片段是字符串编写，以$开头用于表示方法或构造函数参数或方法返回值
 */
public class ServiceUtils {
    public static void done() throws NotFoundException, CannotCompileException {
        //获取默认的类池
        ClassPool classPool = ClassPool.getDefault();
        //通过全限定类名从类池获取对应需要增强的类
        CtClass base = classPool.getOrNull("bytecodeenhancer.BaseService");
        if (base  == null){
            System.out.println("can not found");
            return;
        }
        //通过方法getDeclaredMethod和类中需要增强的方法名字得到CtMethod类型的方法抽象
        CtMethod basemethod = base.getDeclaredMethod("basePrint");
        //组合增强字符串，使用$1获取方法的参数。
        StringBuffer sbf = new StringBuffer();
        sbf.append("{");
        sbf.append("bytecodeenhancer.ExBaseService.exPrint($1);");
        sbf.append("}");

        //进行增强,调用之前增强
        basemethod.insertBefore(sbf.toString());

        StringBuffer sbf2 = new StringBuffer();
        sbf2.append("{");
        sbf2.append("bytecodeenhancer.ExBaseService.exPrintL($1);");
        sbf2.append("}");

        //进行增强,调用需要增强方法之后增强
        basemethod.insertAfter(sbf2.toString());
        //替换增强后的字节码
        base.toClass();
    }
}
