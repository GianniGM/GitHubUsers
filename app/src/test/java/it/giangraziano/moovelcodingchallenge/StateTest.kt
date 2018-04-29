package it.giangraziano.moovelcodingchallenge

import it.giangraziano.moovelcodingchallenge.model.GitHubStateImpl
import it.giangraziano.moovelcodingchallenge.model.GitHubUser
import it.giangraziano.moovelcodingchallenge.model.Response
import it.giangraziano.moovelcodingchallenge.network.GitHubService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class StateTest {

    @get:Rule
    public var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    public var service: GitHubService? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @InjectMocks
    public var state: GitHubStateImpl? = null

    private fun generateUsers (number: Int): Response {
        //mocked manually because of trouble with mockito
        val list = mutableListOf<GitHubUser>()
        for(i in 0 until number){
            list.add(GitHubUser())
        }

        return Response(30, false, list)
    }

    @Test
    fun onServeOneTime_requestSuccess_pageIncremented() {

        val initialPage = state?.currentPage

        val testObserver = state?.getGitHubUsersFromApi()?.test()
        testObserver?.awaitTerminalEvent()
        testObserver?.assertNoErrors()

        val currentPage = state?.currentPage
        if (initialPage != null) {
            assert(currentPage == initialPage + 1)
        }
    }

    @Test
    fun onServeMoreThanOneTime_requestSuccess_pageIncrementedMoreThanOne() {

        val numberOfCalls = 5
        val initialPage = state?.currentPage ?: 1

        for (i in 0 until numberOfCalls) {
            //mock my rxJava
            val testObserver = state?.getGitHubUsersFromApi()?.test()
            testObserver?.awaitTerminalEvent()
            testObserver?.assertNoErrors()
        }

        assert(state?.currentPage == initialPage + numberOfCalls)
    }

    @Test
    fun onSetDevelopers_loadTenUsersFromData_tenUsersAreLoaded() {
        state?.setDevelopers(generateUsers(10))
        assert(state?.totalCountPerPage == 10)
        assert(state?.currentCount == 0)
    }

    @Test
    fun onSetDevelopers_loadMoreThanTenUsersFromData_tenUsersAreLoaded() {
        state?.setDevelopers(generateUsers(30))

        assert(state?.totalCountPerPage!! > 10)
        assert(state?.currentCount == 0)
        state?.loadAlreadyFetchedUsers()
        assert(state?.currentCount == 10)
    }
}
