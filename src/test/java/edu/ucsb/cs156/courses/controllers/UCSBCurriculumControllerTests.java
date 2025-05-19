package edu.ucsb.cs156.courses.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import edu.ucsb.cs156.courses.ControllerTestCase;
import edu.ucsb.cs156.courses.config.SecurityConfig;
import edu.ucsb.cs156.courses.repositories.UserRepository;
import edu.ucsb.cs156.courses.services.UCSBCurriculumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = UCSBCurriculumController.class)
@Import(SecurityConfig.class)
@AutoConfigureDataJpa
public class UCSBCurriculumControllerTests extends ControllerTestCase {

  @MockBean
  UserRepository userRepository;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UCSBCurriculumService ucsbCurriculumService;

  @Test
  public void test_search() throws Exception {

    String expectedResult = "{expectedJSONResult}";
    String urlTemplate = "/api/public/basicsearch?qtr=%s&dept=%s&level=%s";
    String url = String.format(urlTemplate, "20204", "CMPSC", "L");
    when(ucsbCurriculumService.getJSON(any(String.class), any(String.class), any(String.class)))
        .thenReturn(expectedResult);

    MvcResult response = mockMvc
        .perform(get(url).contentType("application/json"))
        .andExpect(status().isOk())
        .andReturn();
    String responseString = response.getResponse().getContentAsString();

    assertEquals(expectedResult, responseString);
  }

  // Tests for the final exam information controller
  @Test
  public void test_finalsInfo() throws Exception {
    String expectedResult = "{expectedJSONResult}";
    String urlTemplate = "/api/public/finalsInfo?quarterYYYYQ=%s&enrollCd=%s";
    String url = String.format(urlTemplate, "20251", "67421");
    when(ucsbCurriculumService.getFinalsInfo(any(String.class), any(String.class)))
        .thenReturn(expectedResult);

    MvcResult response = mockMvc
        .perform(get(url).contentType("application/json"))
        .andExpect(status().isOk())
        .andReturn();
    String responseString = response.getResponse().getContentAsString();

    assertEquals(expectedResult, responseString);
  }

  // Tests for the general education area information controller
  @Test
  public void test_generalEducationAreas() throws Exception {
    String[] expectedResult = {
        "A1", "A2", "AMH", "B", "C", "D", "E", "E1", "E2", "ETH", "EUR", "F", "G", "H", "NWC", "QNT", "SUB", "WRT"
    };

    String url = "/api/public/generalEducationInfo";
    when(ucsbCurriculumService.getGEAreas())
        .thenReturn(expectedResult);

    MvcResult response = mockMvc
        .perform(get(url).contentType("application/json"))
        .andExpect(status().isOk())
        .andReturn();
    String responseString = response.getResponse().getContentAsString();

    ObjectMapper objectMapper = new ObjectMapper();
    String expectedString = objectMapper.writeValueAsString(expectedResult);

    assertEquals(expectedString, responseString);
  }
}
