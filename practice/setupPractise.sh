#sync
git checkout develop
git pull

#make sure you don't have changed files
git status

#create a maven project
touch pom.xml
packages="dataStructure codingChallenge"
for p in $(echo $packages); do
  mkdir -p src/main/java/ca/jrvs/practice/$p src/test/java/ca/jrvs/practice/$p
done

cat > pom.xml << _EOF
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://maven.apache.org/POM/4.0.0"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>ca.jrvs.apps</groupId>
  <artifactId>practice</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <!--Jackson JSON-->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.9.8</version>
    </dependency>
    <!--testing-->
    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>2.23.4</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
_EOF

cat > src/main/java/ca/jrvs/practice/dataStructure/HelloWorld.java << _EOF
package ca.jrvs.practice.dataStructure;

class HelloWorld {

  // Your program begins with a call to main().
  // Prints "Hello, World" to the terminal window.
  public static void main(String args[]) {
    System.out.println("Hello, World");
  }
}
_EOF

#test pom.xml and download all packages
#you should see `[INFO] BUILD SUCCESS`
mvn package

#push change to dev
git add .
git commit -m 'init practice project'
git push

