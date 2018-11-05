package cn.e3mall.freemarker;
import java.io.File;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {
	
	@Test
	public void testFreeMarker() throws Exception {
		
		//创建Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		//设置模板文件
		configuration.setDirectoryForTemplateLoading(new File("D:/workspace/e3-item-web/src/main/webapp/WEB-INF/ftl"));
		//模板文件编码格式
		configuration.setDefaultEncoding("utf-8");
		//加载模板文件 创建模板对象
//		Template template = configuration.getTemplate("hello.ftl");
		Template template = configuration.getTemplate("student.ftl");
		//创建数据集
		Map data = new HashMap<>();
		data.put("hello", "hello freemarker!");
		//创建pojo对象
		Student student = new Student(1,"小明",18,"北京");
		data.put("student", student);
		List<Student> students = new ArrayList<>();
		students.add(new Student(1,"小明1",18,"北京"));
		students.add(new Student(2,"小明2",18,"北京"));
		students.add(new Student(3,"小明3",18,"北京"));
		students.add(new Student(4,"小明4",18,"北京"));
		students.add(new Student(5,"小明5",18,"北京"));
		students.add(new Student(6,"小明6",18,"北京"));
		students.add(new Student(7,"小明7",18,"北京"));
		data.put("students", students);
		//添加日期类型
		data.put("date", new Date());
		//null值的处理
		data.put("val", "123");
		//创建Writer对象 指定输出文件的路径和文件名
//		Writer out = new FileWriter(new File("D:/freemarkertest/hello.txt"));
		Writer out = new FileWriter(new File("D:/freemarkertest/student.html"));
		//生成静态页面
		template.process(data, out);
		//关闭流
		out.close();
		
	}
}
