package Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Utils.Observer;
import model.Sarcina;
import model.ValidatorSarcina;

public class RepositoryFromXMLFile extends AbstractRepository<Integer,Sarcina> {

	private String FileName;
	
	public RepositoryFromXMLFile(String file)
	{
		this.FileName=file;
		validator = new ValidatorSarcina();
		readData();
	}
	
	@Override
	public void save(Sarcina e) throws ExceptiaMea
	{
		super.save(e);
		//this.saveData();
	}
	
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dfb = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc = null;
		try{
			db=dfb.newDocumentBuilder();
		}
		catch(ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		doc=db.newDocument();
		
		Node root = doc.createElement("sarcini");
		doc.appendChild(root);
		for(Sarcina s : entities)
		{
			Element sarcinaElement = doc.createElement("sarcina");
			createElementFromSarcina("id",doc,s.getId()+"",sarcinaElement);
			createElementFromSarcina("descriere",doc,s.getDescriere()+"",sarcinaElement);
			root.appendChild(sarcinaElement);
		}
		
		DOMSource sursa = new DOMSource(doc);
		StreamResult result = new StreamResult(FileName);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
            transformer = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        try {
            transformer.transform(sursa,result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
	}
	
	private void createElementFromSarcina(String fieldName,Document doc,String value,Element parent)
	{
		Element e = doc.createElement(fieldName);
		e.setTextContent(value);
		parent.appendChild(e);
	}
	@Override
	public void readData() {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc=null;
		
		try
		{
			db=dbf.newDocumentBuilder();
		}
		catch(ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		
		try{
			doc=db.parse(new FileInputStream(FileName));
		}
		catch(IOException | SAXException e)
		{
			e.printStackTrace();
		}
		
		Node root = doc.getDocumentElement();
		NodeList nodeList = root.getChildNodes();
		
		for(int i=0;i<nodeList.getLength();i++){
			if(nodeList.item(i).getNodeType()==Node.ELEMENT_NODE)
			{
				Element e = (Element) nodeList.item(i);
				Sarcina s = createSarcinaFromElement(e);
				try
				{
					save(s);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}

	private Sarcina createSarcinaFromElement(Element e) {
		// TODO Auto-generated method stub
		Integer id  = Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent());
		String descriere = e.getElementsByTagName("descriere").item(0).getTextContent();
		Sarcina s = new Sarcina(id,descriere);
		return s;
	}

	private List<Observer<Sarcina>> observers = new ArrayList<>();
	@Override
	public void addObserver(Observer<Sarcina> o) {
		// TODO Auto-generated method stub
		System.out.println("Add observer"+o.getClass());
        observers.add(o);
	}

	@Override
	public void removeObserver(Observer<Sarcina> o) {
		// TODO Auto-generated method stub
		System.out.println("Remove observer"+o.getClass());
        observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer o : observers){
            o.update(this);
        }
	}

	public boolean existaObject(Sarcina s)
	{
		for(Sarcina obj:entities)
		{
			//System.out.println(obj);
			//System.out.println(s);
			if(obj.equals(s))
			{
				//System.out.println("postul exista");
				return true;
			}
		}
		return false;
	}

}
