package com.github.ashim.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ashim.documentation.model.Person;

/**
 * Created by ashimjk on 10/12/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PersonDocumentation {

	@Rule
	public final RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	private RestDocumentationResultHandler document;

	@Before
	public void setUp() {
		this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation)).alwaysDo(this.document).build();
	}

	@Test
	public void listPeople() throws Exception {
		// createSamplePerson("Vivek", "Hamal");
		// createSamplePerson("Sameer", "Twayana");

		this.document.snippets(responseFields(fieldWithPath("[].id").description("The persons' ID"),
				fieldWithPath("[].firstName").description("The persons' first name"),
				fieldWithPath("[].lastName").description("The persons' last name")));

		this.mockMvc.perform(get("/people").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getPerson() throws Exception {
		// Person samplePerson = createSamplePerson("Nabin", "Ghimire");

		this.document.snippets(responseFields(fieldWithPath("id").description("The person's ID"),
				fieldWithPath("firstName").description("The persons' first name"),
				fieldWithPath("lastName").description("The persons' last name")));

		this.mockMvc.perform(get("/people/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void createPerson() throws Exception {
		Map<String, String> newPerson = new HashMap<>();
		newPerson.put("firstName", "Pranes");
		newPerson.put("lastName", "Basnet");

		ConstrainedFields fields = new ConstrainedFields(Person.class);

		this.document.snippets(requestFields(fields.withPath("firstName").description("The persons' first name"),
				fields.withPath("lastName").description("The persons' last name")));

		this.mockMvc.perform(post("/people").contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(newPerson))).andExpect(status().isCreated());
	}

	@Test
	public void updatePerson() throws Exception {
		// Person originalPerson = createSamplePerson("Sameer", "Twayana");
		Map<String, String> updatedPerson = new HashMap<>();
		updatedPerson.put("firstName", "Susan");
		updatedPerson.put("lastName", "Tandhukar");

		ConstrainedFields fields = new ConstrainedFields(Person.class);

		this.document.snippets(requestFields(fields.withPath("firstName").description("The persons' first name"),
				fields.withPath("lastName").description("The persons' last name")));

		this.mockMvc
				.perform(put("/people/3").contentType(MediaType.APPLICATION_JSON)
						.content(this.objectMapper.writeValueAsString(updatedPerson)))
				.andExpect(status().isNoContent());
	}

	// private Person createSamplePerson(String firstName, String lastName) {
	// return this.personRepository.save(new Person(firstName, lastName));
	// }

	private static class ConstrainedFields {

		private final ConstraintDescriptions constraintDescriptions;

		ConstrainedFields(Class<?> input) {
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}

		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints").value(StringUtils
					.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
		}
	}

}
