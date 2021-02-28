object CommonRepos {

    object Gradle {
        object Buildscript {
            const val gradle = "https://plugins.gradle.org/m2/"
        }
    }

    object FunczzGithub {
        //Gradle: Deprecated `http` protocol.
        const val releases = "https://funczz.github.io/mvnrepository/releases"
        const val snapshots = "https://funczz.github.io/mvnrepository/snapshots"
    }

    object Jitpack {
        const val releases = "https://jitpack.io"
    }

    object Sonatype {
        const val snapshots = "https://oss.sonatype.org/content/repositories/snapshots"
    }

}
