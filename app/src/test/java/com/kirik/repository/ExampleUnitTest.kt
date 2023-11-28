package com.kirik.repository

import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkModel() {
        val json = "{\n" +
                "    \"total_count\": 15296175,\n" +
                "    \"incomplete_results\": true,\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"id\": 107462598,\n" +
                "            \"node_id\": \"MDEwOlJlcG9zaXRvcnkxMDc0NjI1OTg=\",\n" +
                "            \"name\": \"test model\",\n" +
                "            \"full_name\": \"binder-examples/r\",\n" +
                "            \"private\": false,\n" +
                "            \"owner\": {\n" +
                "                \"login\": \"binder-examples\",\n" +
                "                \"id\": 30417857,\n" +
                "                \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjMwNDE3ODU3\",\n" +
                "                \"avatar_url\": \"https://avatars.githubusercontent.com/u/30417857?v=4\",\n" +
                "                \"gravatar_id\": \"\",\n" +
                "                \"url\": \"https://api.github.com/users/binder-examples\",\n" +
                "                \"html_url\": \"https://github.com/binder-examples\",\n" +
                "                \"followers_url\": \"https://api.github.com/users/binder-examples/followers\",\n" +
                "                \"following_url\": \"https://api.github.com/users/binder-examples/following{/other_user}\",\n" +
                "                \"gists_url\": \"https://api.github.com/users/binder-examples/gists{/gist_id}\",\n" +
                "                \"starred_url\": \"https://api.github.com/users/binder-examples/starred{/owner}{/repo}\",\n" +
                "                \"subscriptions_url\": \"https://api.github.com/users/binder-examples/subscriptions\",\n" +
                "                \"organizations_url\": \"https://api.github.com/users/binder-examples/orgs\",\n" +
                "                \"repos_url\": \"https://api.github.com/users/binder-examples/repos\",\n" +
                "                \"events_url\": \"https://api.github.com/users/binder-examples/events{/privacy}\",\n" +
                "                \"received_events_url\": \"https://api.github.com/users/binder-examples/received_events\",\n" +
                "                \"type\": \"Organization\",\n" +
                "                \"site_admin\": false\n" +
                "            },\n" +
                "            \"html_url\": \"https://github.com/binder-examples/r\",\n" +
                "            \"description\": \"Using R with Jupyter / RStudio on Binder\",\n" +
                "            \"fork\": false,\n" +
                "            \"url\": \"https://api.github.com/repos/binder-examples/r\",\n" +
                "            \"forks_url\": \"https://api.github.com/repos/binder-examples/r/forks\",\n" +
                "            \"keys_url\": \"https://api.github.com/repos/binder-examples/r/keys{/key_id}\",\n" +
                "            \"collaborators_url\": \"https://api.github.com/repos/binder-examples/r/collaborators{/collaborator}\",\n" +
                "            \"teams_url\": \"https://api.github.com/repos/binder-examples/r/teams\",\n" +
                "            \"hooks_url\": \"https://api.github.com/repos/binder-examples/r/hooks\",\n" +
                "            \"issue_events_url\": \"https://api.github.com/repos/binder-examples/r/issues/events{/number}\",\n" +
                "            \"events_url\": \"https://api.github.com/repos/binder-examples/r/events\",\n" +
                "            \"assignees_url\": \"https://api.github.com/repos/binder-examples/r/assignees{/user}\",\n" +
                "            \"branches_url\": \"https://api.github.com/repos/binder-examples/r/branches{/branch}\",\n" +
                "            \"tags_url\": \"https://api.github.com/repos/binder-examples/r/tags\",\n" +
                "            \"blobs_url\": \"https://api.github.com/repos/binder-examples/r/git/blobs{/sha}\",\n" +
                "            \"git_tags_url\": \"https://api.github.com/repos/binder-examples/r/git/tags{/sha}\",\n" +
                "            \"git_refs_url\": \"https://api.github.com/repos/binder-examples/r/git/refs{/sha}\",\n" +
                "            \"trees_url\": \"https://api.github.com/repos/binder-examples/r/git/trees{/sha}\",\n" +
                "            \"statuses_url\": \"https://api.github.com/repos/binder-examples/r/statuses/{sha}\",\n" +
                "            \"languages_url\": \"https://api.github.com/repos/binder-examples/r/languages\",\n" +
                "            \"stargazers_url\": \"https://api.github.com/repos/binder-examples/r/stargazers\",\n" +
                "            \"contributors_url\": \"https://api.github.com/repos/binder-examples/r/contributors\",\n" +
                "            \"subscribers_url\": \"https://api.github.com/repos/binder-examples/r/subscribers\",\n" +
                "            \"subscription_url\": \"https://api.github.com/repos/binder-examples/r/subscription\",\n" +
                "            \"commits_url\": \"https://api.github.com/repos/binder-examples/r/commits{/sha}\",\n" +
                "            \"git_commits_url\": \"https://api.github.com/repos/binder-examples/r/git/commits{/sha}\",\n" +
                "            \"comments_url\": \"https://api.github.com/repos/binder-examples/r/comments{/number}\",\n" +
                "            \"issue_comment_url\": \"https://api.github.com/repos/binder-examples/r/issues/comments{/number}\",\n" +
                "            \"contents_url\": \"https://api.github.com/repos/binder-examples/r/contents/{+path}\",\n" +
                "            \"compare_url\": \"https://api.github.com/repos/binder-examples/r/compare/{base}...{head}\",\n" +
                "            \"merges_url\": \"https://api.github.com/repos/binder-examples/r/merges\",\n" +
                "            \"archive_url\": \"https://api.github.com/repos/binder-examples/r/{archive_format}{/ref}\",\n" +
                "            \"downloads_url\": \"https://api.github.com/repos/binder-examples/r/downloads\",\n" +
                "            \"issues_url\": \"https://api.github.com/repos/binder-examples/r/issues{/number}\",\n" +
                "            \"pulls_url\": \"https://api.github.com/repos/binder-examples/r/pulls{/number}\",\n" +
                "            \"milestones_url\": \"https://api.github.com/repos/binder-examples/r/milestones{/number}\",\n" +
                "            \"notifications_url\": \"https://api.github.com/repos/binder-examples/r/notifications{?since,all,participating}\",\n" +
                "            \"labels_url\": \"https://api.github.com/repos/binder-examples/r/labels{/name}\",\n" +
                "            \"releases_url\": \"https://api.github.com/repos/binder-examples/r/releases{/id}\",\n" +
                "            \"deployments_url\": \"https://api.github.com/repos/binder-examples/r/deployments\",\n" +
                "            \"created_at\": \"2017-10-18T21:01:56Z\",\n" +
                "            \"updated_at\": \"2023-11-13T03:46:12Z\",\n" +
                "            \"pushed_at\": \"2022-01-17T18:15:33Z\",\n" +
                "            \"git_url\": \"git://github.com/binder-examples/r.git\",\n" +
                "            \"ssh_url\": \"git@github.com:binder-examples/r.git\",\n" +
                "            \"clone_url\": \"https://github.com/binder-examples/r.git\",\n" +
                "            \"svn_url\": \"https://github.com/binder-examples/r\",\n" +
                "            \"homepage\": \"\",\n" +
                "            \"size\": 1651,\n" +
                "            \"stargazers_count\": 206,\n" +
                "            \"watchers_count\": 206,\n" +
                "            \"language\": \"Jupyter Notebook\",\n" +
                "            \"has_issues\": true,\n" +
                "            \"has_projects\": true,\n" +
                "            \"has_downloads\": true,\n" +
                "            \"has_wiki\": true,\n" +
                "            \"has_pages\": false,\n" +
                "            \"has_discussions\": false,\n" +
                "            \"forks_count\": 277,\n" +
                "            \"mirror_url\": null,\n" +
                "            \"archived\": false,\n" +
                "            \"disabled\": false,\n" +
                "            \"open_issues_count\": 7,\n" +
                "            \"license\": {\n" +
                "                \"key\": \"bsd-3-clause\",\n" +
                "                \"name\": \"BSD 3-Clause \\\"New\\\" or \\\"Revised\\\" License\",\n" +
                "                \"spdx_id\": \"BSD-3-Clause\",\n" +
                "                \"url\": \"https://api.github.com/licenses/bsd-3-clause\",\n" +
                "                \"node_id\": \"MDc6TGljZW5zZTU=\"\n" +
                "            },\n" +
                "            \"allow_forking\": true,\n" +
                "            \"is_template\": true,\n" +
                "            \"web_commit_signoff_required\": false,\n" +
                "            \"topics\": [\n" +
                "                \"binder\",\n" +
                "                \"binder-ready\"\n" +
                "            ],\n" +
                "            \"visibility\": \"public\",\n" +
                "            \"forks\": 277,\n" +
                "            \"open_issues\": 7,\n" +
                "            \"watchers\": 206,\n" +
                "            \"default_branch\": \"master\",\n" +
                "            \"score\": 1.0\n" +
                "        }\n" +
                "    ]\n" +
                "}"
        val m = Gson().fromJson(json, RepositoryResponse::class.java)
        assert(m.totalCount != 0)
    }
}