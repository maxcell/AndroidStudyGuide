package com.adammcneilly.androidstudyguide.articlelist

import com.adammcneilly.androidstudyguide.fakes.FakeArticleRepository
import com.adammcneilly.androidstudyguide.models.Article
import com.adammcneilly.androidstudyguide.testObserver
import com.google.common.truth.Truth.assertThat

class ArticleListViewModelRobot {
    private lateinit var viewModel: ArticleListViewModel
    private val fakeRepository = FakeArticleRepository()

    fun emitArticles(articles: List<Article>) = apply {
        fakeRepository.emitArticles(articles)
    }

    fun buildViewModel() = apply {
        viewModel = ArticleListViewModel(
            articleRepository = fakeRepository
        )
    }

    fun assertViewState(expectedViewState: ArticleListViewState) = apply {
        val actualViewState = viewModel.state.testObserver().observedValue
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }

    fun assertNumberOfCallsToFetchArticles(expectedCalls: Int) = apply {
        val actualCalls = fakeRepository.getFetchedArticlesCallCount()
        assertThat(actualCalls).isEqualTo(expectedCalls)
    }
}