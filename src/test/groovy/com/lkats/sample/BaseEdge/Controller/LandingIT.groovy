package com.lkats.sample.BaseEdge.Controller

import groovy.json.JsonSlurper

import static org.springframework.http.HttpStatus.*
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class LandingIT extends Specification {

    def landing = new Landing()
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(landing).build()

    def 'simulate http request to GET / end-point'() {
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders
                        .get('/landing')
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().response

        then:
        response.status == OK.value()
        response.contentType.contains('application/json')
        response.contentAsString == 'Hello.'
    }

    def 'simulate http request to POST / end-point'() {
        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders
                        .post('/landing')
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content('{"name": "lkats"}'))
                .andReturn().response

        def payload = new JsonSlurper().parseText(response.contentAsString)

        then:
        response.status == ACCEPTED.value()
        response.contentType.contains('application/json')
        response.contentType == 'application/json;charset=UTF-8'

        payload.name == 'lkats'
    }
}
