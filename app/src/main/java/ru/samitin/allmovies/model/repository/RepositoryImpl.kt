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
        movies= listOf<Movie>(Movie(title= "Космический джем: Новое поколение",release_date = "08 июль 2021",vote_average = 7.8,
                overview ="Чтобы спасти сына, знаменитый чемпион НБА отправляется в сказочный мир," +
                        " где в команде мультяшек вынужден сражаться на баскетбольной площадке с " +
                        "цифровыми копиями знаменитых игроков.",image = 0),

              Movie(title = "Чёрная вдова",image = 1,vote_average = 7.9,release_date = "07 июл 2021",
                overview = "Наташе Романофф предстоит лицом к лицу встретиться со своим прошлым. Чёрной Вдове придется вспомнить о том," +
                        " что было в её жизни задолго до присоединения к команде Мстителей, и узнать об опасном заговоре," +
                        " в который оказываются втянуты её старые знакомые — Елена, Алексей (известный как Красный Страж) и Мелина."),

               Movie(title="Судная ночь навсегда",image =2,vote_average =7.8,release_date ="30 июн 2021",
                       overview ="Этим летом все правила будут нарушены. Группа мародеров решает," +
                       " что ежегодная Судная ночь не должна заканчиваться с наступлением утра, " +
                       "а может продолжаться бесконечно. Никто больше не будет в безопасности."),
               Movie(title="Босс-молокосос 2",image =3,vote_average =7.9,release_date ="01 июл 2021",
                       overview ="Продолжение приключений героев мультфильма, с которыми зрители познакомились в предыдущей части \"Босс-молокосос\" 2017 года."),
               Movie(title="Война будущего",image =4,vote_average =8.2,release_date ="30 июн 2021",
                       overview ="В будущем идёт разрушительный конфликт с инопланетной расой." +
                       " В попытке переломить ход войны учёные начинают призывать в свою армию солдат из прошлого."))
        categories= listOf(Category("Боевики", movies),
                Category("Комедии", movies),
                Category("Фантастика", movies),
                Category("Ужасы", movies),
                Category("Мультфильмы", movies))

    }
    override fun getMoviesFromLocalStorage(): List<Category> = categories
    override fun getMoviesFromServer(): List<Category> = categories

}