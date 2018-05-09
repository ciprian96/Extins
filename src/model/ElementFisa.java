package model;

public class ElementFisa implements HasId<Integer> {
	private Integer id;
//	private Integer idPost;
//	private String denumirePost;
//	private String tipPost;
//	private Double SalariuPost;
//	private Integer idSarcina;
//	private String descriereSarcina;
	
	private Post post;
	private Sarcina sarcina;
	
	public ElementFisa(Integer id, Post post, Sarcina sarcina) {
		super();
		this.id = id;
		this.post = post;
		this.sarcina = sarcina;
	}
	

//	public ElementFisa(Integer id, Integer idPost, String denumirePost, String tipPost, Double salariuPost,Integer idSarcina, String descriereSarcina) {
//		super();
//		this.id = id;
//		this.idPost = idPost;
//		this.denumirePost = denumirePost;
//		this.tipPost = tipPost;
//		SalariuPost = salariuPost;
//		this.idSarcina = idSarcina;
//		this.descriereSarcina = descriereSarcina;
//	}


//	public Integer getIdPost() {
//		return idPost;
//	}
//
//
//	public void setIdPost(Integer idPost) {
//		this.idPost = idPost;
//	}
//
//
//	public String getDenumirePost() {
//		return denumirePost;
//	}
//
//
//	public void setDenumirePost(String denumirePost) {
//		this.denumirePost = denumirePost;
//	}
//
//
//	public String getTipPost() {
//		return tipPost;
//	}
//
//
//	public void setTipPost(String tipPost) {
//		this.tipPost = tipPost;
//	}
//
//
//	public Double getSalariuPost() {
//		return SalariuPost;
//	}
//
//
//	public void setSalariuPost(Double salariuPost) {
//		SalariuPost = salariuPost;
//	}
//
//
//	public Integer getIdSarcina() {
//		return idSarcina;
//	}
//
//
//	public void setIdSarcina(Integer idSarcina) {
//		this.idSarcina = idSarcina;
//	}
//
//
//	public String getDescriereSarcina() {
//		return descriereSarcina;
//	}
//
//
//	public void setDescriereSarcina(String descriereSarcina) {
//		this.descriereSarcina = descriereSarcina;
//	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Sarcina getSarcina() {
		return sarcina;
	}

	public void setSarcina(Sarcina sarcina) {
		this.sarcina = sarcina;
	}
	
	public String toString()
	{
		return id+" \n"+post+ sarcina; 
	}
	
}
