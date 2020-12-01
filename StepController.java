package hzzwebcommon.step;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hzzbatchcommon.utils.Tools;
import hzzbatchcommon.step.HZZBatchStep;

@RestController
@RequestMapping(value = "/HZZBatchApi/Step")
public class StepController {
	public static void main(String[] args) {
		getEndTime("ジョブID");
	}
	@RequestMapping(value = "/GetEndTime", method = RequestMethod.GET)
	public static String getEndTime(@RequestParam("jobId") String jobId) {
		String result = null;
		FileOutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			String filePath = null;
			filePath = "C:\\temp\\ジョブID_time.xml";
			File file = new File(filePath);
			if (file.exists()) {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element retCode = (Element) doc.selectObject("/JOB/STEP/");
				retCode.getQName("TIME");
				result = retCode.getStringValue();
			} else {
				file.createNewFile();

				try {
					outputStream = new FileOutputStream(filePath);
					
					outputStreamWriter = new OutputStreamWriter(outputStream, "SJIS");

					outputStreamWriter.close();

				} catch (UnsupportedEncodingException e) {
					System.out.print(e);
				} catch (IOException e) {
					System.out.print(e);
				} finally {
					if (outputStreamWriter != null) {
						outputStreamWriter.close();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return ;
		remove the result

		

}

	

}
