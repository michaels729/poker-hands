# Poker Hands
An application that evaluates two Poker hands and determines the winner.

### How to Build
1. Install the [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
and a compatible version of Maven. This application was tested
with [Maven 3.5.2](https://archive.apache.org/dist/maven/maven-3/3.5.2/binaries/).
You can find instructions to install Maven [here](https://maven.apache.org/install.html).
2. From the root of the cloned repo, run the following:
<pre><code>mvn clean package</code></pre>

The command above will build an executable JAR file and run a suite of unit
tests. The JAR will be in the <code>target</code> directory that gets created.

### How to execute
You will need to create a test file with 5 lines representing cards. Each line
should adhere to the following format:

<pre><code>RANK SUIT</code></pre>

You can refer to the files in the <code>test-hands</code> directory for
examples.

After you've create the hand files, run the following from the root of this
project's directory:
<pre><code>java -jar target/poker.app.evaluator-1.0-SNAPSHOT.jar &lt;path_to_hand1&gt; &lt;path_to_hand2&gt;</code></pre>
