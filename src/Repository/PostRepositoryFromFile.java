package Repository;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

//import com.itextpdf.text.Anchor;
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.Chapter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.ListItem;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Section;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;

import Utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Post;
import model.Sarcina;
import model.ValidatorPost;

import java.io.FileOutputStream;
import java.util.Date;

//import com.itextpdf.text.Anchor;
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chapter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
////import com.itextpdf.text.List;
//import com.itextpdf.text.ListItem;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Section;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;

public class PostRepositoryFromFile extends AbstractRepository<Integer,Post> {
	protected String fileName;

	public PostRepositoryFromFile(String file)
	{
		super();
		validator = new ValidatorPost();
		this.fileName=file;
		readData();
	}
	@Override
	public void save(Post p) throws ExceptiaMea
	{
		super.save(p);
		//this.saveData();
	}

	@Override
	public void updateObject(Integer id, Post e) throws ExceptiaMea
	{
		super.updateObject(id, e);
		//this.saveData();
	}

	@Override
	public Post delete(Integer id) throws ExceptiaMea
	{
		Post e= super.delete(id);
		//this.saveData();
		return e;
	}

	@Override
	public void readData() {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		try{
			String line;
			br=new BufferedReader(new FileReader(fileName));
			while((line=br.readLine())!=null)
			{
				//System.out.println(line);
				String[] fields = line.split("\\|");
				Post p =new Post(Integer.parseInt(fields[0]),fields[1],fields[2],Double.parseDouble(fields[3]));
				try{
					validator.validate(p);
					this.entities.add(p);
				}
				catch(ExceptiaMea e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Nu se gaseste fisierul");
		}
		catch(IOException e)
		{
			System.out.println("Nu pot citi");
		}
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		try{
			bw=new BufferedWriter(new FileWriter(fileName));
			//bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			String line;
			for(Post p : this.entities)
			{
				line="";
				line=p.getId()+"|"+p.getDenumire()+"|"+p.getTip()+"|"+p.getSalariu()+"\n";
				bw.write(line);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally{
			if(bw!=null)
				try{
					bw.close();
				}
			catch(IOException e)
			{
				System.out.println("BufferedWriter nu poate fi inchis");
			}
		}
	}
	public ObservableList<Post> getPosturi(String text) {
		// TODO Auto-generated method stub
		//ObservableList<Post> posts = null;
		List<Post> posturs = new ArrayList<>();

		entities.forEach(e->{
			if(e.getDenumire().startsWith(text))
				posturs.add(e);
		});
		ObservableList<Post> posts = FXCollections.observableArrayList(posturs);
		//System.out.println(posts.size());
		return posts;
	}

	private List<Observer<Post>> observers = new ArrayList<>();

	    @Override
	    public void addObserver(Observer<Post> o) {
	        System.out.println("Add observer"+o.getClass());
	        observers.add(o);
	    }

	    @Override
	    public void removeObserver(Observer<Post> o) {
	        System.out.println("Remove observer"+o.getClass());
	        observers.remove(o);
	    }

	    @Override
	    public void notifyObservers() {
	        for (Observer o : observers){
	            o.update(this);
	        }
	    }

	    public boolean existaObject(Post p)
		{
			for(Post post:entities)
			{
				//System.out.println(post);
				//System.out.println(p);
				if(post.equals(p))
				{
					//System.out.println("postul exista");
					return true;
				}
			}
			return false;
		}

//	    private static String FILE = "./PostPdf.pdf";
//
//		private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
//
//	    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//		                        Font.NORMAL, BaseColor.RED);
//		private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
//		                        Font.BOLD);
//		private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//		                        Font.BOLD);
//
//		public void savePostToPdf() {
//			try {
//		           Document document = new Document();
//		           PdfWriter.getInstance(document, new FileOutputStream(FILE));
//		           document.open();
//		           addMetaData(document);
//		           addTitlePage(document);
//		           addContent(document);
//		           document.close();
//			} catch (Exception e) {
//                    e.printStackTrace();
//                   }
//	}
//
//		        // iText allows to add metadata to the PDF which can be viewed in your Adobe
//		        // Reader
//		        // under File -> Properties
//		public void addMetaData(Document document) {
//				document.addSubject("Using iText");
//				document.addKeywords("Java, PDF, iText");
//				document.addAuthor("Turca Ciprian");
//				document.addCreator("Turca Ciprian");
//		  }
//
//		public void addTitlePage(Document document)
//				throws DocumentException {
//				Paragraph preface = new Paragraph();
//				// We add one empty line
//				addEmptyLine(preface, 1);
//
//				// Lets write a big header
//				preface.add(new Paragraph("Repository de posturi", catFont));
//
//				addEmptyLine(preface, 1);
//				// Will create: Report generated by: _name, _date
//				preface.add(new Paragraph(
//						"Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//						smallBold));
//				addEmptyLine(preface, 3);
//				preface.add(new Paragraph(
//                        "This document describes something which is very important ", smallBold));
//
//				addEmptyLine(preface, 8);
//
//				document.add(preface);
//		                // Start a new page
//		        document.newPage();
//		}
//
//		public void addContent(Document document) throws DocumentException {
//
//				Anchor anchor = new Anchor("First Chapter", catFont);
//				anchor.setName("First Chapter");
//
//		                // Second parameter is the number of the chapter
//				Chapter catPart = new Chapter(new Paragraph(anchor), 1);
//
//		                //com.itextpdf.text.Font subFont = null;
//				Paragraph subPara = new Paragraph("Subcategory 1", subFont);
//				Section subCatPart = catPart.addSection(subPara);
//				subCatPart.add(new Paragraph("Bine ati venit !!!"));
//
////				Paragraph subPara = new Paragraph("Subcategory 2", subFont);
////				subCatPart = catPart.addSection(subPara);
////				subCatPart.add(new Paragraph("Paragraph 1"));
////				subCatPart.add(new Paragraph("Paragraph 2"));
////				subCatPart.add(new Paragraph("Paragraph 3"));
//
//				// add a list
//				//createList(subCatPart);
//
//				Paragraph paragraph = new Paragraph();
//				addEmptyLine(paragraph, 5);
//				subCatPart.add(paragraph);
//
//				// add a table
//				createTable(subCatPart);
//
//				// now add all this to the document
//
//				document.add(catPart);
//
//				// Next section
//				anchor = new Anchor("Second Chapter", catFont);
//				anchor.setName("Second Chapter");
//
//				// Second parameter is the number of the chapter
//				catPart = new Chapter(new Paragraph(anchor), 1);
//
//				subPara = new Paragraph("Subcategory", subFont);
//				subCatPart = catPart.addSection(subPara);
//				subCatPart.add(new Paragraph("This is a very important message"));
//
//				// now add all this to the document
//				document.add(catPart);
//
//		}
//
//		public void createTable(Section subCatPart) throws BadElementException {
//			PdfPTable table = new PdfPTable(4);
//
//			// t.setBorderColor(BaseColor.GRAY);
//            // t.setPadding(4);
//			// t.setSpacing(4);
//            // t.setBorderWidth(1);
//
//			PdfPCell c1 = new PdfPCell(new Phrase("Id Post"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//
//            c1 = new PdfPCell(new Phrase("Denumire Post"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//
//            c1 = new PdfPCell(new Phrase("Tip Post"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//
//            c1 = new PdfPCell(new Phrase("Salariu Post"));
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(c1);
//
//            table.setHeaderRows(1);
//
//            this.entities.forEach(e->{
//            	table.addCell(e.getId().toString());
//            	table.addCell(e.getDenumire());
//            	table.addCell(e.getTip());
//            	table.addCell(e.getSalariu().toString());
//            });
//
//            subCatPart.add(table);
//
//		}
//
//
//		public void addEmptyLine(Paragraph paragraph, int number) {
//            for (int i = 0; i < number; i++) {
//            	paragraph.add(new Paragraph(" "));
//            }
//		}
}
