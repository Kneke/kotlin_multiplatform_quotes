package loader

import de.kneke.common.data.quote.Quote
import de.kneke.common.util.logger.log
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json.Companion.parse
import kotlinx.serialization.list
import java.lang.Exception

class QuoteLoader {

    @UnstableDefault
    fun getQuotesFromResources(): List<Quote> {
        return try {
            val fileContent = this::class.java.classLoader.getResource("quotes.json")!!.readText()
            log("LOAD FROM RESSOURCE")
            parse(Quote.serializer().list, fileContent)
        } catch (e: Exception) {
            println(e)
            log("LOAD FROM OBJECTS")
            listOf(
                Quote(
                    45,
                    "Clean structured working code makes me horny.",
                    "Christoph Knetschke",
                    "http://quotes.stormconsultancy.co.uk/quotes/45"
                ),
                Quote(
                    44,
                    "QA Engineer walks into a bar. Orders a beer. Orders 0 beers. Orders 999999999 beers. Orders a lizard. Orders -1 beers. Orders a sfdeljknesv.",
                    "Bill Sempf",
                    "http://quotes.stormconsultancy.co.uk/quotes/44"
                ),
                Quote(
                    43,
                    "There are only two hard things in Computer Science: cache invalidation, naming things and off-by-one errors.",
                    "Phil Karlton",
                    "http://quotes.stormconsultancy.co.uk/quotes/43"
                ),
                Quote(
                    42,
                    "In software, we rarely have meaningful requirements. Even if we do, the only measure of success that matters is whether our solution solves the customer\u2019s shifting idea of what their problem is.",
                    "Jeff Atwood",
                    "http://quotes.stormconsultancy.co.uk/quotes/42"
                ),
                Quote(
                    41,
                    "If Java had true garbage collection, most programs would delete themselves upon execution.",
                    "Robert Sewell",
                    "http://quotes.stormconsultancy.co.uk/quotes/41"
                ),
                Quote(
                    40,
                    "C++ : Where friends have access to your private members.",
                    "Gavin Russell Baker",
                    "http://quotes.stormconsultancy.co.uk/quotes/40"
                ),
                Quote(
                    39,
                    "In C++ it\u2019s harder to shoot yourself in the foot, but when you do, you blow off your whole leg.",
                    "Bjarne Stroustrup",
                    "http://quotes.stormconsultancy.co.uk/quotes/39"
                ),
                Quote(
                    38,
                    "Most software today is very much like an Egyptian pyramid with millions of bricks piled on top of each other, with no structural integrity, but just done by brute force and thousands of slaves.",
                    "Alan Kay",
                    "http://quotes.stormconsultancy.co.uk/quotes/38"
                ),
                Quote(
                    37,
                    "I\u2019ve noticed lately that the paranoid fear of computers becoming intelligent and taking over the world has almost entirely disappeared from the common culture.  Near as I can tell, this coincides with the release of MS-DOS.",
                    "Larry DeLuca",
                    "http://quotes.stormconsultancy.co.uk/quotes/37"
                ),
                Quote(
                    36,
                    "No matter how slick the demo is in rehearsal, when you do it in front of a live audience, the probability of a flawless presentation is inversely proportional to the number of people watching, raised to the power of the amount of money involved.",
                    "Mark Gibbs",
                    "http://quotes.stormconsultancy.co.uk/quotes/36"
                ),
                Quote(
                    35,
                    "The most amazing achievement of the computer software industry is its continuing cancellation of the steady and staggering gains made by the computer hardware industry.",
                    "Henry Petroski",
                    "http://quotes.stormconsultancy.co.uk/quotes/35"
                ),
                Quote(
                    34,
                    "There are two major products that come out of Berkeley: LSD and UNIX.  We don\u2019t believe this to be a coincidence.",
                    "Jeremy S. Anderson",
                    "http://quotes.stormconsultancy.co.uk/quotes/34"
                ),
                Quote(
                    33,
                    "Computers are like bikinis. They save people a lot of guesswork.",
                    "Sam Ewing",
                    "http://quotes.stormconsultancy.co.uk/quotes/33"
                ),
                Quote(
                    32,
                    "Linux is only free if your time has no value.",
                    "Jamie Zawinski",
                    "http://quotes.stormconsultancy.co.uk/quotes/32"
                ),
                Quote(
                    31,
                    "Documentation is like sex; when it's good, it's very, very good, and when it's bad, it's better than nothing.",
                    "Dick Brandon",
                    "http://quotes.stormconsultancy.co.uk/quotes/31"
                ),
                Quote(
                    30,
                    "The difference between theory and practice is that in theory, there is no difference between theory and practice.",
                    "Richard Moore",
                    "http://quotes.stormconsultancy.co.uk/quotes/30"
                ),
                Quote(
                    29,
                    "Programming is like sex: one mistake and you\u2019re providing support for a lifetime.",
                    "Michael Sinz",
                    "http://quotes.stormconsultancy.co.uk/quotes/29"
                ),
                Quote(
                    28,
                    "There are only two kinds of programming languages: those people always bitch about and those nobody uses.",
                    "Bjarne Stroustrup",
                    "http://quotes.stormconsultancy.co.uk/quotes/28"
                ),
                Quote(
                    27,
                    "Beware of bugs in the above code; I have only proved it correct, not tried it. ",
                    "Donald Knuth",
                    "http://quotes.stormconsultancy.co.uk/quotes/27"
                ),
                Quote(
                    26,
                    "We know about as much about software quality problems as they knew about the Black Plague in the 1600s. We\u2019ve seen the victims\u2019 agonies and helped burn the corpses. We don\u2019t know what causes it; we don\u2019t really know if there is only one disease. We just suffer \u2014 and keep pouring our sewage into our water supply.",
                    "Tom Van Vleck",
                    "http://quotes.stormconsultancy.co.uk/quotes/26"
                ),
                Quote(
                    25,
                    "Writing the first 90 percent of a computer program takes 90 percent of the time. The remaining ten percent also takes 90 percent of the time and the final touches also take 90 percent of the time.",
                    "N.J. Rubenking",
                    "http://quotes.stormconsultancy.co.uk/quotes/25"
                ),
                Quote(
                    24,
                    "There are two ways of constructing a software design; one way is to make it so simple that there are obviously no deficiencies, and the other way is to make it so complicated that there are no obvious deficiencies. The first method is far more difficult.",
                    "C. A. R. Hoare",
                    "http://quotes.stormconsultancy.co.uk/quotes/24"
                ),
                Quote(
                    23,
                    "You should name a variable using the same care with which you name a first-born child.",
                    "James O. Coplien",
                    "http://quotes.stormconsultancy.co.uk/quotes/23"
                ),
                Quote(
                    22,
                    "Einstein argued that there must be simplified explanations of nature, because God is not capricious or arbitrary. No such faith comforts the software engineer.",
                    "Fred Brooks",
                    "http://quotes.stormconsultancy.co.uk/quotes/22"
                ),
                Quote(
                    21,
                    "XML is like violence \u2013 if it doesn\u2019t solve your problems, you are not using enough of it.",
                    "Unknown",
                    "http://quotes.stormconsultancy.co.uk/quotes/21"
                ),
                Quote(
                    20,
                    "Saying that Java is good because it works on all platforms is like saying anal sex is good because it works on all genders.",
                    "Unknown",
                    "http://quotes.stormconsultancy.co.uk/quotes/20"
                ),
                Quote(
                    19,
                    "I love deadlines. I like the whooshing sound they make as they fly by.",
                    "Douglas Adams",
                    "http://quotes.stormconsultancy.co.uk/quotes/19"
                ),
                Quote(
                    18,
                    "Perl \u2013 The only language that looks the same before and after RSA encryption.",
                    "Keith Bostic",
                    "http://quotes.stormconsultancy.co.uk/quotes/18"
                ),
                Quote(
                    17,
                    "Two things are infinite: the universe and human stupidity; and I\u2019m not sure about the universe.",
                    "Albert Einstein",
                    "http://quotes.stormconsultancy.co.uk/quotes/17"
                ),
                Quote(
                    16,
                    "In theory, theory and practice are the same. In practice, they\u2019re not.",
                    "Yogi Berra",
                    "http://quotes.stormconsultancy.co.uk/quotes/16"
                ),
                Quote(
                    15,
                    "It is practically impossible to teach good programming style to students that have had prior exposure to BASIC. As potential programmers, they are mentally mutilated beyond hope of regeneration.",
                    "E. W. Dijkstra",
                    "http://quotes.stormconsultancy.co.uk/quotes/15"
                ),
                Quote(
                    14,
                    "If debugging is the process of removing software bugs, then programming must be the process of putting them in.",
                    "E. W. Dijkstra",
                    "http://quotes.stormconsultancy.co.uk/quotes/14"
                ),
                Quote(
                    13,
                    "A computer lets you make more mistakes faster than any other invention in human history, with the possible exceptions of handguns and tequila.",
                    "Mitch Ratcliffe",
                    "http://quotes.stormconsultancy.co.uk/quotes/13"
                ),
                Quote(
                    12,
                    "I have always wished for my computer to be as easy to use as my telephone; my wish has come true because I can no longer figure out how to use my telephone.",
                    "Bjarne Stroustrup",
                    "http://quotes.stormconsultancy.co.uk/quotes/12"
                ),
                Quote(
                    11,
                    "I don\u2019t care if it works on your machine! We are not shipping your machine!",
                    "Ovidiu Platon",
                    "http://quotes.stormconsultancy.co.uk/quotes/11"
                ),
                Quote(
                    10,
                    "Programming today is a race between software engineers striving to build bigger and better idiot-proof programs, and the Universe trying to produce bigger and better idiots. So far, the Universe is winning.",
                    "Rich Cook",
                    "http://quotes.stormconsultancy.co.uk/quotes/10"
                ),
                Quote(
                    9,
                    "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.",
                    "Rick Osborne",
                    "http://quotes.stormconsultancy.co.uk/quotes/9"
                ),
                Quote(
                    8,
                    "On two occasions I have been asked, \u2018Pray, Mr. Babbage, if you put into the machine wrong figures, will the right answers come out?\u2019 I am not able rightly to apprehend the kind of confusion of ideas that could provoke such a question.\u201d",
                    "Charles Babbage",
                    "http://quotes.stormconsultancy.co.uk/quotes/8"
                ),
                Quote(
                    7,
                    "PHP is a minor evil perpetrated and created by incompetent amateurs, whereas Perl is a great and insidious evil, perpetrated by skilled but perverted professionals.",
                    "Jon Ribbens",
                    "http://quotes.stormconsultancy.co.uk/quotes/7"
                ),
                Quote(
                    6,
                    "Measuring programming progress by lines of code is like measuring aircraft building progress by weight.",
                    "Bill Gates",
                    "http://quotes.stormconsultancy.co.uk/quotes/6"
                ),
                Quote(
                    5,
                    "Debugging is twice as hard as writing the code in the first place. Therefore, if you write the code as cleverly as possible, you are, by definition, not smart enough to debug it.",
                    "Brian Kernighan",
                    "http://quotes.stormconsultancy.co.uk/quotes/5"
                ),
                Quote(
                    4,
                    "Some people, when confronted with a problem, think \u201cI know, I\u2019ll use regular expressions.\u201d Now they have two problems.",
                    "Jamie Zawinski",
                    "http://quotes.stormconsultancy.co.uk/quotes/4"
                ),
                Quote(
                    3,
                    "It always takes longer than you expect, even when you take into account Hofstadter\u2019s Law.",
                    "Hofstadter\u2019s Law",
                    "http://quotes.stormconsultancy.co.uk/quotes/3"
                ),
                Quote(
                    2,
                    "Walking on water and developing software from a specification are easy if both are frozen.",
                    "Edward V Berard",
                    "http://quotes.stormconsultancy.co.uk/quotes/2"
                ),
                Quote(
                    1,
                    "We should forget about small efficiencies, say about 97% of the time: premature optimization is the root of all evil.",
                    "C. A. R. Hoare",
                    "http://quotes.stormconsultancy.co.uk/quotes/1"
                )
            )
        }
    }
}