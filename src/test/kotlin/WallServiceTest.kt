import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val post = Post(0, ownerId = 1, fromId = 1, date = 22052023, text = "second", likes = Likes())
        val result = WallService.add(post)
        assertNotNull(result.id)
    }

    @Test
    fun updateCorrectId() {
        val post1 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "first", likes = Likes())
        val post2 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "second", likes = Likes())
        val post3 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "third", likes = Likes())
        val newPost = Post(3, ownerId = 1, fromId = 1, date = 22052023, text = "corrected post third", likes = Likes())
        WallService.add(post1)
        WallService.add(post2)
        WallService.add(post3)

        val result = WallService.update(newPost)
        assertTrue(result)
    }

    @Test
    fun updateUncorrectedId() {
        val post1 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "first", likes = Likes())
        val post2 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "second", likes = Likes())
        val post3 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "third", likes = Likes())
        val newPost = Post(4, ownerId = 1, fromId = 1, date = 22052023, text = "corrected post third", likes = Likes())
        WallService.add(post1)
        WallService.add(post2)
        WallService.add(post3)

        val result = WallService.update(newPost)
        assertFalse(result)
    }
}