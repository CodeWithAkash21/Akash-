package com.example.data

data class ProjectItem(
  val id: String,
  val name: String,
  val category: String,
  val status: String, // SHIPPED, IN DEVELOPMENT, CONCEPT, EXPERIMENT
  val tagline: String,
  val description: String,
  val toolsOrFeatures: List<String>,
  val techStack: List<String>,
  val liveUrl: String? = null,
  val githubUrl: String? = null,
  val isFeatured: Boolean = false,
)

data class CreativeItem(
  val id: String,
  val title: String,
  val discipline: String, // VIDEO, DESIGN, MOTION, AI VISUALS
  val category: String, // SHORT FORM, PRODUCT VIDEOS, CINEMATIC EDITS, MOTION GRAPHICS, UI DESIGN
  val status: String, // PERSONAL EXPERIMENT, CONCEPT
  val description: String,
  val tools: List<String>,
)

data class InterestNode(
  val name: String,
  val status: String, // EXPLORING, LEARNING, BUILDING, EXPERIMENTING
  val category: String,
)

data class AiLabItem(
  val name: String,
  val tag: String,
  val description: String,
  val tech: List<String>,
)

data class HackathonEntry(
  val event: String,
  val year: String,
  val status: String, // PARTICIPANT, REGISTERED
  val projectOrIdea: String,
  val details: String,
)

data class MilestoneItem(
  val title: String,
  val metricOrLabel: String,
  val subtitle: String,
  val category: String,
  val iconName: String,
)

data class TimelineNode(
  val phase: String,
  val title: String,
  val description: String,
  val tag: String,
)

data class MindsetRule(
  val number: String,
  val title: String,
  val description: String,
)

data class BuildLogItem(
  val timestamp: String,
  val action: String,
  val detail: String,
)

data class ToolGroup(
  val category: String,
  val items: List<String>,
)

object PortfolioRepository {

  val projects = listOf(
    ProjectItem(
      id = "greptools",
      name = "grepTools.dev",
      category = "DEVELOPER TOOLS",
      status = "SHIPPED",
      tagline = "Focused developer utilities built for speed and clarity.",
      description = "A collection of focused developer utilities designed to make common developer workflows faster and simpler. Solves repetitive developer tasks with zero clutter.",
      toolsOrFeatures = listOf(
        "JSON Formatter",
        "Base64 Encoder / Decoder",
        "URL Encoder / Decoder",
        "UUID Generator",
        "Unix Timestamp Converter"
      ),
      techStack = listOf("Next.js", "TypeScript", "Tailwind CSS", "Framer Motion", "Vercel"),
      liveUrl = "https://greptools.vercel.app/",
      githubUrl = "https://github.com/CodeWithAkash21/greptools",
      isFeatured = true,
    ),
    ProjectItem(
      id = "studyshare",
      name = "StudyShare AI / NoteMind",
      category = "AI / EDTECH",
      status = "IN DEVELOPMENT",
      tagline = "AI-powered contextual learning workspace for documents & research.",
      description = "An AI-powered learning workspace designed around study material, document understanding, summarization and interactive question answering.",
      toolsOrFeatures = listOf(
        "PDF extraction",
        "Contextual Summarization",
        "AI Q&A Engine",
        "Interactive Chat",
        "Study Assistance"
      ),
      techStack = listOf("AI", "Python", "Next.js", "Supabase"),
      liveUrl = null,
      githubUrl = null,
      isFeatured = true,
    ),
    ProjectItem(
      id = "saas_experiments",
      name = "SaaS Experiments",
      category = "SAAS",
      status = "IN DEVELOPMENT",
      tagline = "Micro-tools and automated workflow products.",
      description = "Modular SaaS concepts exploring subscription models, automated data processing, and lightweight utility hubs.",
      toolsOrFeatures = listOf("Micro-billing flows", "Modular APIs", "Webhooks", "Self-service portal"),
      techStack = listOf("Next.js", "Node.js", "Supabase", "Stripe API"),
      isFeatured = false,
    ),
    ProjectItem(
      id = "automation_pipeline",
      name = "Automation Workflows",
      category = "AUTOMATION",
      status = "EXPERIMENT",
      tagline = "Repetitive task eliminators and pipeline builders.",
      description = "Scripting and AI-driven automation connecting data feeds, notifications, and media processing pipelines.",
      toolsOrFeatures = listOf("Data extraction", "Scheduled triggers", "API gluing", "CLI utilities"),
      techStack = listOf("Python", "APIs", "Webhooks", "Bash"),
      isFeatured = false,
    ),
    ProjectItem(
      id = "creative_web",
      name = "Web Experiences & Interactive Prototypes",
      category = "WEB EXPERIENCES",
      status = "CONCEPT",
      tagline = "Interactive frontends blending typography, motion and creative tech.",
      description = "Explorations into creative micro-interactions, canvas shaders, generative visuals, and cinematic typography.",
      toolsOrFeatures = listOf("Kinetic typography", "Canvas shaders", "Custom physics", "Sound design cues"),
      techStack = listOf("React", "TypeScript", "Tailwind CSS", "Canvas API"),
      isFeatured = false,
    )
  )

  val creativeItems = listOf(
    CreativeItem(
      id = "c1",
      title = "Cinematic Story Cuts & Sound Design",
      discipline = "VIDEO",
      category = "CINEMATIC EDITS",
      status = "PERSONAL EXPERIMENT",
      description = "Rhythmic pacing, color grading in DaVinci Resolve, dynamic sound design and narrative cuts.",
      tools = listOf("DaVinci Resolve", "Premiere Pro", "Audition")
    ),
    CreativeItem(
      id = "c2",
      title = "Product Launch Teasers & Kinetic Typography",
      discipline = "MOTION",
      category = "MOTION GRAPHICS",
      status = "CONCEPT",
      description = "Fast-paced SaaS feature teasers, typography animation, UI mockups in 3D perspective.",
      tools = listOf("After Effects", "Figma", "Motion Blur")
    ),
    CreativeItem(
      id = "c3",
      title = "Short Form Tech & Visual Breakdowns",
      discipline = "VIDEO",
      category = "SHORT FORM",
      status = "PERSONAL EXPERIMENT",
      description = "High-retention storytelling, visual hooks, clean overlays, and concise technical breakdowns.",
      tools = listOf("CapCut Pro", "Premiere", "After Effects")
    ),
    CreativeItem(
      id = "c4",
      title = "Futuristic UI & Brand Systems",
      discipline = "DESIGN",
      category = "UI DESIGN",
      status = "CONCEPT",
      description = "Minimalist dark mode interfaces, design tokens, precision spacing, and editorial typography.",
      tools = listOf("Figma", "Canva", "Design Tokens")
    ),
    CreativeItem(
      id = "c5",
      title = "Generative Visuals & AI Concept Art",
      discipline = "AI VISUALS",
      category = "AI CONTENT",
      status = "PERSONAL EXPERIMENT",
      description = "Exploring multimodal image generation, style consistency, and synthetic visual storytelling.",
      tools = listOf("Multimodal AI", "ComfyUI", "Photoshop")
    )
  )

  val interestConstellation = listOf(
    InterestNode("AI & LLMs", "EXPLORING", "Technology"),
    InterestNode("Machine Learning", "LEARNING", "Technology"),
    InterestNode("SaaS Products", "BUILDING", "Entrepreneurship"),
    InterestNode("Developer Utilities", "BUILDING", "Software"),
    InterestNode("Automation", "EXPERIMENTING", "Engineering"),
    InterestNode("Cybersecurity", "LEARNING", "Security"),
    InterestNode("Modern Web Dev", "BUILDING", "Software"),
    InterestNode("Product Design", "EXPLORING", "Design"),
    InterestNode("Video Storytelling", "EXPERIMENTING", "Creative"),
    InterestNode("Motion Graphics", "EXPLORING", "Creative"),
    InterestNode("Creative AI", "EXPERIMENTING", "Multidisciplinary"),
    InterestNode("Startups & Ideas", "EXPLORING", "Entrepreneurship"),
  )

  val aiLab = listOf(
    AiLabItem(
      name = "LLM Applications & Prompting",
      tag = "ACTIVE EXPERIMENT",
      description = "Context window optimization, structured outputs, JSON mode, and prompt chain orchestration.",
      tech = listOf("Gemini API", "OpenAI", "Prompt Engineering")
    ),
    AiLabItem(
      name = "AI Agents & Autonomous Workflows",
      tag = "RESEARCH",
      description = "Tool calling, recursive reasoning, multi-step task execution, and agentic workflows.",
      tech = listOf("Python", "Function Calling", "LangChain concepts")
    ),
    AiLabItem(
      name = "Document AI & RAG Concepts",
      tag = "PROTOTYPING",
      description = "Document chunking, semantic retrieval, question answering over research papers and notes.",
      tech = listOf("Python", "Embeddings", "Supabase pgvector")
    ),
    AiLabItem(
      name = "Machine Learning & Forecasting",
      tag = "ACADEMIC & APPLIED",
      description = "Classical ML models, tree algorithms, and time-series trend forecasting.",
      tech = listOf("Scikit-learn", "LightGBM", "Prophet", "Python")
    )
  )

  val hackathons = listOf(
    HackathonEntry(
      event = "AMAZON NOVA AI HACKATHON",
      year = "2026",
      status = "PARTICIPANT",
      projectOrIdea = "Multimodal AI Workflow Exploration",
      details = "Testing state-of-the-art Nova models for rapid inference, document parsing, and agentic reasoning under hackathon constraints."
    ),
    HackathonEntry(
      event = "GOOGLE GEMINI LIVE AGENT CHALLENGE",
      year = "2026",
      status = "PARTICIPANT",
      projectOrIdea = "Live Autonomous Agent Architectures",
      details = "Exploring real-time multimodal streaming, voice/text interactivity, and system-level agent tooling with Gemini."
    )
  )

  val milestones = listOf(
    MilestoneItem(
      title = "BCA COMPLETED",
      metricOrLabel = "GRADUATE",
      subtitle = "Bachelor of Computer Applications\nKLE Society's Jagadguru Tontadarya College, Gadag, Karnataka",
      category = "EDUCATION",
      iconName = "School"
    ),
    MilestoneItem(
      title = "ACADEMIC SCORE",
      metricOrLabel = "8.2",
      subtitle = "Cumulative Grade Point Average (CGPA) reflecting strong computer science foundations.",
      category = "EXCELLENCE",
      iconName = "Grade"
    ),
    MilestoneItem(
      title = "grepTools.dev",
      metricOrLabel = "SHIPPED",
      subtitle = "Built and deployed a suite of real developer productivity tools on Vercel.",
      category = "SOFTWARE",
      iconName = "RocketLaunch"
    ),
    MilestoneItem(
      title = "AI HACKATHONS",
      metricOrLabel = "2+ ARENAS",
      subtitle = "Verified participant in Amazon Nova AI Hackathon & Google Gemini Live Agent Challenge.",
      category = "EXPERIMENTATION",
      iconName = "Bolt"
    )
  )

  val timeline = listOf(
    TimelineNode(
      phase = "01",
      title = "THE START & FOUNDATION",
      description = "Began exploring logic, algorithms, C, C++, and writing first lines of code. Discovered the thrill of turning logic into output.",
      tag = "ORIGIN"
    ),
    TimelineNode(
      phase = "02",
      title = "LEARNING THE MODERN WEB",
      description = "Dived deep into JavaScript, TypeScript, React, Next.js, and modern styling. Learned how great software feels to real people.",
      tag = "GROWTH"
    ),
    TimelineNode(
      phase = "03",
      title = "DISCOVERING THE CREATIVE SIDE",
      description = "Refused to stay confined to code. Picked up DaVinci Resolve, After Effects, and Figma. Explored visual storytelling, sound, and pacing.",
      tag = "EXPANSION"
    ),
    TimelineNode(
      phase = "04",
      title = "SHIPPING REAL TOOLS",
      description = "Shipped grepTools.dev to solve actual developer friction. Experience the true discipline of taking a product from zero to deployed URL.",
      tag = "SHIPPED"
    ),
    TimelineNode(
      phase = "05",
      title = "EXPLORING THE AI FRONTIER",
      description = "Diving into LLMs, Gemini, OpenAI, Scikit-learn, Hackathons, and building StudyShare AI. Pushing into agentic automation.",
      tag = "PRESENT"
    ),
    TimelineNode(
      phase = "06",
      title = "WHAT'S NEXT: I'M STILL BUILDING",
      description = "Scaling into full SaaS products, creative technology, autonomous tools, and entrepreneurship. No boundaries, no single box.",
      tag = "HORIZON"
    )
  )

  val mindset = listOf(
    MindsetRule("01", "START WITH AN IDEA.", "Everything begins as a spark of curiosity or a problem worth solving."),
    MindsetRule("02", "MAKE IT REAL.", "Ideas without execution are nothing. Write the code. Open the timeline."),
    MindsetRule("03", "MAKE IT USEFUL.", "Focus on utility. Remove fluff. Ensure it solves a genuine human need."),
    MindsetRule("04", "MAKE IT BEAUTIFUL.", "Aesthetics and typography convey respect for the user's attention."),
    MindsetRule("05", "SHIP IT.", "Never hide work in local folders forever. Put it in the wild."),
    MindsetRule("06", "LEARN.", "Inspect real behavior, mistakes, and feedback without defensive pride."),
    MindsetRule("07", "BUILD AGAIN.", "Iterate, level up, and tackle harder, more ambitious challenges.")
  )

  val buildLog = listOf(
    BuildLogItem("2026.09", "GREPTOOLS.DEV SHIPPED", "Live on Vercel with JSON Formatter, Base64, UUID Generator, and URL tools."),
    BuildLogItem("2026", "AI PRODUCT EXPERIMENTS", "StudyShare AI / NoteMind document understanding architecture initiated."),
    BuildLogItem("2026", "HACKATHON PARTICIPATION", "Registered and submitted in Amazon Nova & Google Gemini challenges."),
    BuildLogItem("2026", "CREATIVE TECH EXPLORATION", "Cinematic motion cuts, DaVinci Resolve grading, and interface design."),
    BuildLogItem("CURRENT", "BUILDING THE NEXT THING", "Merging AI agents, SaaS utility architectures, and video storytelling.")
  )

  val toolbox = listOf(
    ToolGroup("CODE", listOf("Python", "TypeScript", "JavaScript", "C", "C++")),
    ToolGroup("WEB", listOf("React", "Next.js", "Tailwind CSS", "HTML5", "CSS3", "Node.js")),
    ToolGroup("AI & ML", listOf("Gemini API", "OpenAI", "Scikit-learn", "LightGBM", "Prophet")),
    ToolGroup("BACKEND", listOf("Supabase", "REST APIs", "SQL Databases", "Vercel Functions")),
    ToolGroup("DESIGN", listOf("Figma", "Canva", "UI Architecture", "Design Systems")),
    ToolGroup("VIDEO & MOTION", listOf("DaVinci Resolve", "Premiere Pro", "After Effects", "CapCut Pro")),
    ToolGroup("DEPLOYMENT", listOf("GitHub", "Vercel", "Git CLI", "CI/CD Workflows"))
  )
}
