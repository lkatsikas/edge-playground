package com.lkats.sample.BaseEdge.Controller

import groovy.json.JsonSlurper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.HttpStatus.ACCEPTED
import static org.springframework.http.HttpStatus.OK

class LoginIT extends Specification {

    def login = new Login()
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(login).build()

    def 'simulate http request to POST / end-point'() {
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders
                        .post('/login')
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content('{"username": "admin", "password": "admin"}'))
                .andReturn().response

        println(response.contentAsString)
        def payload = new JsonSlurper().parseText(response.contentAsString)

        then:
        response.status == ACCEPTED.value()
        response.contentType.contains('application/json')
        response.contentType == 'application/json;charset=UTF-8'

        payload.username == 'admin'
        payload.password == 'admin'
    }
}
