package com.example

import com.example.data.PortfolioRepository
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun testFeaturedProjectIsGrepTools() {
    val greptools = PortfolioRepository.projects.firstOrNull { it.id == "greptools" }
    assertNotNull(greptools)
    assertEquals("grepTools.dev", greptools?.name)
    assertEquals("SHIPPED", greptools?.status)
    assertEquals("https://greptools.vercel.app/", greptools?.liveUrl)
  }

  @Test
  fun testMilestonesVerifiedData() {
    val bcaMilestone = PortfolioRepository.milestones.firstOrNull { it.title.contains("BCA") }
    assertNotNull(bcaMilestone)
    val score = PortfolioRepository.milestones.firstOrNull { it.metricOrLabel == "8.2" }
    assertNotNull(score)
  }

  @Test
  fun testHackathonWallStatus() {
    val hackathons = PortfolioRepository.hackathons
    assertTrue(hackathons.isNotEmpty())
    hackathons.forEach { hack ->
      assertTrue(hack.status == "PARTICIPANT" || hack.status == "REGISTERED")
    }
  }
}

