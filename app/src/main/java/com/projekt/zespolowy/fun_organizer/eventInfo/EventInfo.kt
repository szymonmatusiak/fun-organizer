package com.projekt.zespolowy.fun_organizer.eventInfo

data class EventInfo(val date: String = "",
                     val description: String = "",
                     val id: Int = 0,
                     val name: String = "",
                     val placeName: String = "")

//to powyżej jest stare i częściowo działa (nie wyświetla place name ale przynajmniej działa)
//to poniżej jest wygenerowane na podstawie jsona ze swaggera,
// w logach jest taki sam z tego co widzę a nic z niego nie bierze i klasa jest pusta, chujnia :/ )

/*        val id: Int = -1,
        val name: String = "",
        val address: String = "",
        val date: String = "",
        val description: String = "",
        val latitude: Int = -1,
        val longitude: Int = -1,
        val needs: List<Need>,
        val place: String = "",
        val placeInfo: String = ""
)*/