# Poker Hands
A application that evaluates two Poker hands and determines the winner.

### How to Build
1. Install the Java 8 SDK and a compatible version of Maven. This application
was tested with Maven 3.3.9.
2. From the root of the cloned repo, run the following:
<pre><code>mvn clean package</code></pre>

The command above will build an executable JAR file as well as run a suite of
unit tests. There will be an executable jar in the <code>target</code>
directory created.

### How to execute
You will need to create a test file with 5 lines representing cards. Each line
should follow the following format:

<pre><code>&lt;RANK&gt; &lt;SUIT&gt;</code></pre>

You can refer to the files in the <code>test-hands</code> directory for
examples.

After you've create the hand files, run the following:
<pre><code>java -jar target/poker.app.evaluator-1.0-SNAPSHOT.jar &lt;path_to_hand1&gt; &lt;path_to_hand2&gt;</code></pre>
