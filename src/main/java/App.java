import akka.actor.*;

public class App {
    public static void main(String[] args) throws Exception {
        final ActorSystem system = ActorSystem.create("App");
        final ActorRef echo1 = system.actorOf(Props.create(EchoActor.class), "echo1");
        final ActorRef echo2 = system.actorOf(Props.create(EchoActor.class), "echo2");
        echo1.tell("ping", echo2);
        Thread.sleep(5000);
        system.shutdown();
        system.awaitTermination();
    }

    public static class EchoActor extends UntypedActor {
        public void onReceive(Object message) throws Exception {
            if (message instanceof String) {
                getSender().tell(message, getSelf());
            }
        }
    }
}
