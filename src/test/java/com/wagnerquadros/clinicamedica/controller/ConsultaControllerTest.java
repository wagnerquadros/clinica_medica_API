package com.wagnerquadros.clinicamedica.controller;

import com.wagnerquadros.clinicamedica.entity.consulta.Consulta;
import com.wagnerquadros.clinicamedica.entity.consulta.dto.AgendamentoConsultaDto;
import com.wagnerquadros.clinicamedica.entity.consulta.dto.DetalhamentoConsultaDto;
import com.wagnerquadros.clinicamedica.entity.medico.Especialidade;
import com.wagnerquadros.clinicamedica.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AgendamentoConsultaDto> agendamentoConsultaJson;

    @Autowired
    private JacksonTester<DetalhamentoConsultaDto> detalhamentoConsultaJson;

    @MockitoBean
    private ConsultaService consultaService;

    @Test
    @WithMockUser
    @DisplayName("Deveria devolver código http 400 quando dados não estão inválidos")
    void agendarCenario1() throws Exception {
        var response = mockMvc.perform(post("/consultas"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    @DisplayName("Deveria devolver código http 200 quando dados estão inválidos")
    void agendarCenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DetalhamentoConsultaDto(null, 2L, 5L, data);

        when(consultaService.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mockMvc
                .perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agendamentoConsultaJson.write(
                                new AgendamentoConsultaDto(2L, 5L, data, especialidade)
                        ).getJson())
                )
                .andReturn()
                .getResponse();

        var jsonEsperado = detalhamentoConsultaJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}
