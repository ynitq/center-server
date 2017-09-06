package codeGen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cfido.commons.codeGen.anno.CodeGenEntity;

/**
 * <pre>
 * 启动程序
 * </pre>
 * 
 * @author 梁韦江
 *  2016年6月2日
 */
@SpringBootApplication
@CodeGenEntity
public class CodeGenForCenterServer {

	public static void main(String[] args) {
		SpringApplication.run(CodeGenForCenterServer.class, args);
	}

}
