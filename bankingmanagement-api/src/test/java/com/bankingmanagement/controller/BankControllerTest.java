package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.service.BankService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerTest1 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @Test
    public void getBanks_whenBanksDetailsExist_thenReturnBanks() throws Exception {
        List<BankDTO> bankDTOList = new ArrayList<>();
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankName("SBI");
        bankDTO.setBankAddress("Bangalore");
        bankDTOList.add(bankDTO);

        when(bankService.findAll()).thenReturn(bankDTOList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/banks").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void getBanks_whenBanksDetailsNotExist_thenReturnNotFound() throws Exception {
        List<BankDTO> bankDTOList = null;

        when(bankService.findAll()).thenReturn(bankDTOList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/banks").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

    @Test
    public void getBanks_whenBanksDetailsNotExist_thenThrowBankDetailsNotFoundException() throws Exception {
        when(bankService.findAll()).thenThrow(new BankDetailsNotFound());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/banks").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

    @Test
    public void getBanks_whenBanksDetailsNotExist_thenThrowInternalServerError() throws Exception {
        when(bankService.findAll()).thenThrow(new NullPointerException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/banks").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }

    @Test
    public void getBankByCode_whenBanksDetailsExist_thenReturnBanks() throws Exception {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankName("SBI");
        bankDTO.setBankAddress("Bangalore");

        when(bankService.findBankDetails(anyInt())).thenReturn(bankDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/banks/99").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void getBankByName_whenBanksDetailsExist_thenReturnBanks() throws Exception {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankName("SBI");
        bankDTO.setBankAddress("Bangalore");

        when(bankService.findBankByName(anyString())).thenReturn(bankDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/banks/byname?name=test").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void saveBank_whenValidInput_thenReturnBankDetails() throws Exception {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankName("SBI");
        bankDTO.setBankAddress("Bangalore");

        when(bankService.save(any())).thenReturn(bankDTO);

        BankRequest request = new BankRequest();
        request.setBankName("SBI");
        request.setBankAddress("Bangalore");

        ObjectMapper mapper= new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/banks")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void updateBank_whenValidInput_thenReturnBankDetails() throws Exception {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankName("SBI");
        bankDTO.setBankAddress("Bangalore");

        when(bankService.save(any())).thenReturn(bankDTO);

        BankRequest request = new BankRequest();
        request.setBankName("SBI");
        request.setBankAddress("Bangalore");
        request.setBankCode(1);

        ObjectMapper mapper= new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/banks")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @org.junit.Test
    public void testDelete() throws Exception {
        when(bankService.delete(anyInt())).thenReturn("Bank has been deleted");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/banks?code=99")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    // assignment - all the negative test cases and the test cases for the delete api

}
