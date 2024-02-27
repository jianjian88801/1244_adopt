import com.xunmaw.help.entity.Pet;
import com.xunmaw.help.mapper.PetMapper;
import com.xunmaw.help.service.PetService;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Mr Wu
 * @create: 2019-08-21 23:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:Spring-dao.xml", "classpath:Spring-service.xml" })
public class petTest {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetService petService;

    @Test
    public void select(){
        List<Pet> pets = petMapper.getPets();
        System.out.println(pets);
    }

    @Test
    public void update(){
        PageHelper.startPage(1,3);
        List<Pet> pets = petService.getPets();
        System.out.println(pets);
    }

    @Test
    public void select1(){
        Pet byId = petService.findById(10);
        System.out.println(byId);
    }
}
