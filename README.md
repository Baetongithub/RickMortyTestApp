## Тестовое задание на позицию Intern Android Developer

#Требования по используемым технологиям:
• Kotlin + Kotlin code style
• Single Activity + Clean Architecture + MVVM
• Koin
• Retrofit + OkHTTP
• Paging 3
• Kotlin coroutines + Flow
• ViewPager2
• Navigation Component
• Material Design ( Material Components only )

Необходимо сделать приложение, используя Rick And Morty API ( дизайн на свое усмотрение ) и реализовать следующий функционал:
- Рабочий TabLayout n с тремя экранами :
    -  RecyclerView из персонажей
    -  RecyclerView из локаций
    -  RecyclerView из Эпизодов

- Во всех трех экранах должна присутствовать пагинация и поиск по названию.
- В экране с персонажами должен присутствовать диалог c фильтром по следующим параметрам ( RadioGroup + RadioButtons ):

- Статус ( жив, мертв, неизвестно )
  - Раса ( человек, гуманоид, пришелец )
  - Пол ( мужской, женский, неизвестно )
  - Также кнопка сброса фильтра и его применение соответственно

Будет плюсом :
• Многомодульность
• Gradle Kotlin DSL ( Version Catalog / BuildSrc )
• Jetpack Compose

Ссылки :
• API Documentation : https://rickandmortyapi.com/documentation