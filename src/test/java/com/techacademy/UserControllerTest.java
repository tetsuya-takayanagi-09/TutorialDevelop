package com.techacademy;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.techacademy.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetList() throws Exception {
        // リクエストを実行し、結果を取得
        MvcResult result = mockMvc.perform(get("/user/list"))
            // HTTPステータスが200OKであること
            .andExpect(status().isOk())
            // Modelにuserlistが含まれていること
            .andExpect(model().attributeExists("userlist"))
            // Modelにエラーが無いこと
            .andExpect(model().hasNoErrors())
            // viewの名前が user/list であること
            .andExpect(view().name("user/list"))
            .andReturn();

        // Modelからuserlistを取り出す
        @SuppressWarnings("unchecked")
        List<User> userlist = (List<User>) result.getModelAndView().getModel().get("userlist");

        // 件数が3件であること
        assertEquals(3, userlist.size());

        // userlistから1件ずつ取り出し、idとnameを検証する
        User user1 = userlist.get(0);
        assertEquals(1, user1.getId());
        assertEquals("キラメキ太郎", user1.getName());

        User user2 = userlist.get(1);
        assertEquals(2, user2.getId());
        assertEquals("キラメキ次郎", user2.getName());

        User user3 = userlist.get(2);
        assertEquals(3, user3.getId());
        assertEquals("キラメキ花子", user3.getName());
    }
}
