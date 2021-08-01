package ru.samitin.allmovies.model.repository

import android.annotation.SuppressLint
import android.content.res.Resources
import android.content.res.TypedArray
import ru.samitin.allmovies.R
import ru.samitin.allmovies.model.data.Category
import ru.samitin.allmovies.model.data.Movie

@SuppressLint("ResourceType")
 class RepositoryImpl() :Repository{

    private lateinit var movies:List<Movie>
    private lateinit var categories:List<Category>
   init {
        movies= listOf<Movie>(Movie("Космический джем: Новое поколение",0,78,"08 июль 2021",
                "Чтобы спасти сына, знаменитый чемпион НБА отправляется в сказочный мир," +
                        " где в команде мультяшек вынужден сражаться на баскетбольной площадке с " +
                        "цифровыми копиями знаменитых игроков.","https://youtu.be/1fbGUxW-Ybc"),

              Movie("Чёрная вдова",1,79,"07 июл 2021",
                "Наташе Романофф предстоит лицом к лицу встретиться со своим прошлым. Чёрной Вдове придется вспомнить о том," +
                        " что было в её жизни задолго до присоединения к команде Мстителей, и узнать об опасном заговоре," +
                        " в который оказываются втянуты её старые знакомые — Елена, Алексей (известный как Красный Страж) и Мелина.",
              "https://youtu.be/W7Pl9s3ybvA"),

               Movie("Судная ночь навсегда",2,78,"30 июн 2021",
               "Этим летом все правила будут нарушены. Группа мародеров решает," +
                       " что ежегодная Судная ночь не должна заканчиваться с наступлением утра, " +
                       "а может продолжаться бесконечно. Никто больше не будет в безопасности.",
               "https://youtu.be/xOrXpK-rUaI"),
               Movie("Босс-молокосос 2",3,79,"01 июл 2021",
               "Продолжение приключений героев мультфильма, с которыми зрители познакомились в предыдущей части \"Босс-молокосос\" 2017 года.",
               "https://youtu.be/qoEUEWCtKZ4"),
               Movie("Война будущего",4,82,"30 июн 2021",
               "В будущем идёт разрушительный конфликт с инопланетной расой." +
                       " В попытке переломить ход войны учёные начинают призывать в свою армию солдат из прошлого.",
               "https://youtu.be/-GP-YpXgZNE"))
        categories= listOf(Category("Боевики", movies),
                Category("Комедии", movies),
                Category("Фантастика", movies),
                Category("Ужасы", movies),
                Category("Мультфильмы", movies))

    }
    override fun getMoviesFromLocalStorage(): List<Category> = categories
    override fun getMoviesFromServer(): List<Category> = categories

}