package Tests;

import model.Post;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestPost {
    private static Post post;

    @BeforeClass
    public static void initialize(){
        post = new Post(1,"Director","parttime",4000.0);
    }

    @Test
    public void testPost(){
//        assertEquals(true,post.getId()==1);
//        assertEquals(true,post.getId()!=0);
//        assertEquals(true,post.getDenumire().equalsIgnoreCase("director"));
//        assertEquals(false,post.getTip().equalsIgnoreCase("fulltime"));
//        assertEquals(true,post.getSalariu()==4000.0);
    }

    @Test
    public void testSetId(){
        post.setId(5);
        int nr=5;
        assertTrue (nr == post.getId());
    }

    @Test
    public void testSetDenumire(){
        Post p = new Post(2,"abc","fulltime",300.0);
        p.setDenumire("Manager");
        Assert.assertEquals(p.getDenumire(), "Manager");
    }
}
