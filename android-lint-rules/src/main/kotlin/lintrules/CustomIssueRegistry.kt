package lintrules


import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class CustomIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> get() = listOf(FUN_NAME_LENGTH_ISSUE)

    override val api: Int get() = CURRENT_API
}
