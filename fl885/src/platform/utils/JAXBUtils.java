package platform.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtils {

	public static Marshaller generateXmlDocument(String instanceName) {
		try {
			JAXBContext context = JAXBContext.newInstance(instanceName);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// �����ʽ
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // �Ƿ��ʽ��xml�ַ���
			return marshaller;
		} catch (JAXBException e) {
			System.out.println("JAXB��ȡʵ��������xmlʱʧ�ܣ�");
			e.printStackTrace();
		}
		return null;
	}

	public static Unmarshaller readXmlDocument(String instanceName) {
		try {
			JAXBContext context = JAXBContext.newInstance(instanceName);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller;
		} catch (JAXBException e) {
			System.out.println("JAXB��ȡʵ��������xmlʱʧ�ܣ�");
			e.printStackTrace();
		}
		return null;
	}

}
