import com.flixclusive.model.provider.Language
import com.flixclusive.model.provider.ProviderType
import com.flixclusive.model.provider.Status

dependencies {
    /*
     * Custom dependencies for each provider should be implemented here.
     * */
    // implementation( ... )

    // Comment if not implementing own SettingsScreen
    // No need to specify compose version explicitly
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.runtime:runtime")
    // ================= END: COMPOSE UI =================

}

/*
* BIG TIP: Just hover on a property and it should provide
* the KDoc/information on what that property does.
*
* If there's no documentation popping up then just
* re-download the source indices.
* */
flxProvider {
    // ====== Provider Information =======
    /**
     * VERY IMPORTANT TO SET. Hover `id` property to see information.
     */
    id = "prov-templ-web-dummy"

    adult = false
    providerName = "WebView Test Provider" // <-- You can customize the name by uncommenting this one.
    description = "A dummy provider that does nothing."

    versionMajor = 1
    versionMinor = 0
    versionPatch = 0
    versionBuild = 0

    /**
     * Changelog of your provider updates. This supports markdown
     * */
    changelog = """
    # Header
    ## Secondary header
    ---
    
    List
    - Item 1
    - Item 2
    - Item 3
    """.trimIndent() // OPTIONAL

    /**
     * Add additional authors to this plugin
     * */
    // author("FirstAuthor")
    // author("SecondAuthor")
    // author( ... )

    /**
     * If your provider has an icon, put its image url here.
     * */
    // iconUrl.set("https://cool.png") // OPTIONAL

    /**
     * The main language of your provider.
     *
     * There are two supported values:
     * - Language.Multiple
     *      > Obviously for providers w/ multiple language support.
     * - Language("en")
     *      > For specific languages only. NOTE: Use the language's short-hand code.
     */
    language = Language.Multiple

    /**
     * The main type that your provider supports.
     *
     * These are the possible values you could set:
     * - ProviderType.All
     * - ProviderType.TvShows
     * - ProviderType.Movies
     * - ProviderType(customType: String) // i.e., ProviderType("Anime")
     */
    providerType = ProviderType.All

    /**
     * The current status of this provider.
     *
     * These are the possible values you could set:
     * - Status.Beta
     * - Status.Maintenance
     * - Status.Down
     * - Status.Working
     */
    status = Status.Working
    // ================


    // === Utilities ===
    /**
     * Toggle this if this provider has its own resources.
     */
    requiresResources = true

    /**
     * Excludes this plugin from the updater,
     * meaning it won't show up for users.
     * Set this if the plugin is unfinished
     */
    // excludeFromUpdaterJson.set(true) // OPTIONAL
    // =================
}

