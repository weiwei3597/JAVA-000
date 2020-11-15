/**
 *
 **/

import com.alibaba.fastjson.JSON;
import com.bean.Class;
import com.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author weiwei
 * @Date 2020-11-13 10:25
 * @description bean装配测试
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BeanAssembleTest {

    @Qualifier("beanAssembleXmlSetTest1")
    @Autowired
    private Student studentSet;

    @Qualifier("beanAssembleXmlSetTest2")
    @Autowired
    private Student studentSet2;

    @Qualifier("beanAssembleXmlSetTest3")
    @Autowired
    private Class classTestSet;

    @Qualifier("beanAssembleConstructorXmlTest1")
    @Autowired
    private Student studentConstructor;

    @Qualifier("beanAssembleXmlConstructorTest2")
    @Autowired
    private Student studentConstructor2;

    @Qualifier("beanAssembleXmlConstructorTest3")
    @Autowired
    private Class classTestConstructor;

    @Resource(name = "beanAssembleFactoryXmlTest")
    private Student studentFactoryBean;

    @Resource(name = "beanAssembleAnnotationSetTest1")
    private Student student1;

    @Resource(name = "beanAssembleAnnotationSetTest2")
    private Student student2;

    @Resource(name = "beanAssembleAnnotationSetTest3")
    private Class student3;

    @Test
    public void test() {
        System.out.println(JSON.toJSONString(studentSet));
        System.out.println(JSON.toJSONString(studentSet2));
        System.out.println(JSON.toJSONString(classTestSet));
    }

    @Test
    public void test2() {
        System.out.println(JSON.toJSONString(studentConstructor));
        System.out.println(JSON.toJSONString(studentConstructor2));
        System.out.println(JSON.toJSONString(classTestConstructor));
    }

    @Test
    public void test3() {
        System.out.println(JSON.toJSONString(studentFactoryBean));
    }

    @Test
    public void test4() {
        System.out.println(JSON.toJSONString(student1));
        System.out.println(JSON.toJSONString(student2));
        System.out.println(JSON.toJSONString(student3));
    }
}
