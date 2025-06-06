package com.nathancabrita.origin.energy.url.shortener.application.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.nathancabrita.origin.energy.url.shortener.application.BaseTest
import com.nathancabrita.origin.energy.url.shortener.application.domain.LongUrl
import com.nathancabrita.origin.energy.url.shortener.application.exception.ShortUrlNotFoundException
import com.nathancabrita.origin.energy.url.shortener.application.exception.ValidationException
import com.nathancabrita.origin.energy.url.shortener.application.service.UrlService
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@AutoConfigureMockMvc
class UrlShortenerControllerTest extends BaseTest {
    private UrlShortenerController controller
    private UrlService urlService
    private MockMvc mockMvc
    private ObjectMapper objectMapper
    private final String GET_SHORT_URL_REQUEST_PATH = "/short-url"
    private final String GET_REDIRECT_TO_LONG_URL_REQUEST_PATH = "/{shortUrlKey}"
    private final String GET_LONG_URL_INFO_REQUEST_PATH = "/{shortUrlKey}/info"


    def setup(){
        urlService = Mock(UrlService)
        controller = new UrlShortenerController(urlService)
        mockMvc = standaloneSetup(controller).build()
        objectMapper = new ObjectMapper()
        objectMapper.registerModule(new JavaTimeModule())
    }

    def "handles the getShortUrl request"(){
        when:
        def response = mockMvc.perform(get(GET_SHORT_URL_REQUEST_PATH)
                .param('url', originalLongUrl))
                .andReturn().response

        then:
        1 * urlService.shortenAndStoreLongUrl(originalLongUrl) >> shortUrl
        assert response.status == HttpStatus.OK.value()
        assert response.contentAsString == shortUrl.toString()
    }

    def "handles the getShortUrl request when invalid url is entered with HTTP 400"(){
        when:
        def response = mockMvc.perform(get(GET_SHORT_URL_REQUEST_PATH)
                .param('url', invalidLongUrl))
                .andReturn().response

        then:
        1 * urlService.shortenAndStoreLongUrl(invalidLongUrl) >> { throw new ValidationException("") }
        assert response.status == HttpStatus.BAD_REQUEST.value()
    }

    def "handles the getShortUrl request when server has internal error with HTTP 500"(){
        when:
        def response = mockMvc.perform(get(GET_SHORT_URL_REQUEST_PATH)
                .param('url', invalidLongUrl))
                .andReturn().response

        then:
        1 * urlService.shortenAndStoreLongUrl(invalidLongUrl) >> { throw new Exception("") }
        assert response.status == HttpStatus.INTERNAL_SERVER_ERROR.value()
    }


    def "handles the redirectToLongUrl request"(){
        when:
        def response = mockMvc.perform(get(GET_REDIRECT_TO_LONG_URL_REQUEST_PATH, shortUrlKey))
                .andReturn().response

        then:
        1 * urlService.getLongUrl(shortUrlKey) >> originalLongUrl
        assert response.status == HttpStatus.FOUND.value()
        assert response.getHeader("Location") == originalLongUrl
    }

    def "handles the redirectToLongUrl request when short url cannot be found with HTTP 404"(){
        when:
        def response = mockMvc.perform(get(GET_REDIRECT_TO_LONG_URL_REQUEST_PATH, shortUrlKey))
                .andReturn().response

        then:
        1 * urlService.getLongUrl(shortUrlKey) >> { throw new ShortUrlNotFoundException("") }
        assert response.status == HttpStatus.NOT_FOUND.value()
    }

    def "handles the redirectToLongUrl request when server has internal error with HTTP 500"(){
        when:
        def response = mockMvc.perform(get(GET_REDIRECT_TO_LONG_URL_REQUEST_PATH, shortUrlKey))
                .andReturn().response
        then:
        1 * urlService.getLongUrl(shortUrlKey) >> { throw new Exception("") }
        assert response.status == HttpStatus.INTERNAL_SERVER_ERROR.value()
    }

    def "handles the getLongUrlInfo request"(){
        when:
        def response = mockMvc.perform(get(GET_LONG_URL_INFO_REQUEST_PATH, shortUrlKey))
                .andReturn().response

        then:
        1 * urlService.getLongUrlInfo(shortUrlKey) >> longUrl
        assert response.status == HttpStatus.OK.value()
        assert response.getContentAsString() == objectMapper.writeValueAsString(longUrl)
    }

    def "handles the getLongUrlInfo request when short url cannot be found with HTTP 404"(){
        when:
        def response = mockMvc.perform(get(GET_LONG_URL_INFO_REQUEST_PATH, shortUrlKey))
                .andReturn().response

        then:
        1 * urlService.getLongUrlInfo(shortUrlKey) >> { throw new ShortUrlNotFoundException("") }
        assert response.status == HttpStatus.NOT_FOUND.value()
    }

    def "handles the getLongUrlInfo request when server has internal error with HTTP 500"(){
        when:
        def response = mockMvc.perform(get(GET_LONG_URL_INFO_REQUEST_PATH, shortUrlKey))
                .andReturn().response
        then:
        1 * urlService.getLongUrlInfo(shortUrlKey) >> { throw new Exception("") }
        assert response.status == HttpStatus.INTERNAL_SERVER_ERROR.value()
    }
}
