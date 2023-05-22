fun main() {
    val post1 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "first", likes = Likes())
    val post2 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "second", likes = Likes())
    val post3 = Post(ownerId = 1, fromId = 1, date = 22052023, text = "third", likes = Likes())
    val newPost3 = Post(3, ownerId = 1, fromId = 1, date = 22052023, text = "corrected post third", likes = Likes())

    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)

    WallService.update(newPost3)

    WallService.likeById(3)
    WallService.likeById(1)
    WallService.likeById(1)
    WallService.likeById(2)

    WallService.printAr()

}

object WallService {
    private var posts = emptyArray<Post>()
    private var uniqueId: Int = 0

    fun add(post: Post): Post {
        uniqueId++
        posts += post.copy(id = uniqueId)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postAr) in posts.withIndex()) {
            if (post.id == postAr.id) {
                posts[index] = post
                return true
            }
        }
        return false
    }

    fun likeById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                val countLike = posts[index].likes.count + 1
                posts[index] = post.copy(likes = Likes(count = countLike))
            }
        }
    }

    fun printAr() {
        for (post in posts) {
            println(post)
        }
    }

    fun clear() {
        posts = emptyArray()
        uniqueId = 0 // также здесь нужно сбросить счетчик для id постов, если он у вас используется
    }
}

data class Post(
        val id: Int = 0,
        val ownerId: Int,
        val fromId: Int,
        val date: Int,
        val text: String,
        val friendsOnly: Boolean = true,
        val postType: String = "post",
        val canEdit: Boolean = true,
        val markedAsAds: Boolean = true,
        val isFavorite: Boolean = true,
        val likes: Likes
)

data class Likes(
        val count: Int = 0,
        val userLikes: Boolean = true,
        val canLike: Boolean = true,
        val canPublish: Boolean = true
)