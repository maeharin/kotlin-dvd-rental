package com.maeharin.kotlindvdrental.integration

import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class FilmRestAPIIntegrationTest : IntegrationTestBase() {
    @Test
    fun shouldGetFilmById() {
        val request = get("/api/v1/films/1")

        mockMvc.perform(request)
                .andExpect(status().isOk())
    }

    @Test
    fun notFoundIfIdNotExists() {
        val request = get("/api/v1/films/0")

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
    }

    @Test
    fun createFilm() {
        val json = """
            {
              "title": "映画タイトル",
              "description": "映画説明",
              "releaseYear": 2017,
              "rentalDuration": 0,
              "rentalRate": 0,
              "length": 0,
              "replacementCost": 0,
              "languageId": 1,
              "actorIds": [1,2],
              "categoryIds": [1,2]
            }
        """.trimIndent()

        val postRequest = post("/api/v1/films")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)

        val id = mockMvc.perform(postRequest)
                .andExpect(status().isCreated())
                .andReturn().response.contentAsString

        val getRequest = get("/api/v1/films/${id}")

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("映画タイトル"))
    }
}