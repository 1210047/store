package com.nkb.store.mapper;


import com.nkb.store.entity.User;
import com.nkb.store.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
// RunWith：表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("user01");
        user.setPassword("123456");
        userService.reg(user);

    }

    @Test
    public void findByUsername() {
        String username = "user01";
        User result = userMapper.selectByUsername(username);
        System.out.println(result);
    }

    @Test
    public void test1(){
        long count = 2000;
        Long a1 = new Long(1);
        int countint = Math.toIntExact(a1);

        System.out.println(count);
        System.out.println(a1);
        System.out.println(countint);
        System.out.println(a1==0);
    }
}
