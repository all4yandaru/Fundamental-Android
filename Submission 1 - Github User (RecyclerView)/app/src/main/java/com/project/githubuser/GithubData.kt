package com.project.githubuser

class GithubData {

    companion object{

        val username = arrayOf(
            "JakeWharton",
            "amitshekhariitbhu",
            "romainguy",
            "chrisbanes",
            "tipsy",
            "ravi8x",
            "jasoet",
            "budioktaviyan",
            "hendisantika",
            "sidiqpermana"
        )

        val name = arrayOf(
            "Jake Wharton",
            "AMIT SHEKHAR",
            "Romain Guy",
            "Chris Banes",
            "David",
            "Ravi Tamada",
            "Deny Prasetyo",
            "Budi Oktaviyan",
            "Hendi Santika",
            "Sidiq Permana"
        )

        val avatar = intArrayOf(
            R.drawable.user1,
            R.drawable.user2,
            R.drawable.user3,
            R.drawable.user4,
            R.drawable.user5,
            R.drawable.user6,
            R.drawable.user7,
            R.drawable.user8,
            R.drawable.user9,
            R.drawable.user10
        )

        val follower = intArrayOf(
            56995,
            5153,
            7972,
            14725,
            788,
            18628,
            277,
            178,
            428,
            465
        )

        val following = intArrayOf(
            12,
            2,
            0,
            1,
            0,
            3,
            39,
            23,
            61,
            10
        )

        val company = arrayOf(
            "Google, Inc.",
            "@MindOrksOpenSource",
            "Google",
            "@google working on @android",
            "Working Group Two",
            "AndroidHive | Droid5",
            "@gojek-engineering",
            "@KotlinID",
            "@JVMDeveloperID @KotlinID @IDDevOps",
            "Nusantara Beta Studio"
        )

        val location = arrayOf(
            "Pittsburgh, PA, USA",
            "New Delhi, India",
            "California",
            "Sydney, Australia",
            "Trondheim, Norway",
            "India",
            "Kotagede, Yogyakarta, Indonesia",
            "Jakarta, Indonesia",
            "Bojongsoang - Bandung Jawa Barat",
            "Jakarta Indonesia"
        )
        val repository = intArrayOf(
            102,
            37,
            9,
            30,
            56,
            28,
            44,
            110,
            1064,
            65
        )

        fun getListData() : ArrayList<GithubUser> {
            var list = arrayListOf<GithubUser>()

            for (i in 0..username.size-1){
                val githubUser = GithubUser(username[i], name[i], avatar[i], follower[i], following[i], company[i], location[i], repository[i])
                list.add(githubUser)
            }
            return list
        }
    }
}