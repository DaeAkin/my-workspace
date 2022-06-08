# WebRTC를 위한 시그널링 서버 구축 (코틀린)

# WebRTC

메이저 브라우저인 크롬, 파이어폭스, 오페라, 마이크로소프트 엣지와, 안드로이드 기기, iOS 기기에서 WebRTC를 지원한다.

WebRTC는 내부적으로 설치할 플러그인이 전혀 필요하지 않다.

게다가,  영상과 음성을 포함하는 전형적인 실시간 어플리케이션에서는 다음과 같은 내용을 제어하기 위해 C++ 라이브러리에 의존했어야 했다.

- 패킷-손실 은닉
- 에코 제거
- 대역폭 관리
- Dynamic jitter buffering
- Automatic gain control
- Noise reduction and suppression
- Image “cleaning”

이런 것들을 이제 WebRTC가 알아서 핸들링 해준다.

## Peer to Peer 연결

Unlike a client-server communication, where there's a known address for the server, and the client already knows the address of the server to communicate with, in a P2P (peer-to-peer) connection, **none of the peers has a direct address to another peer**
.

P2P 연결을 맺기위해서는 클라이언트가 할 몇가지 절차가 존재한다.

- 통신 가능한 상태로 만들기
- 서로 식별하고, 네트워크 정보 공유하기
- 데이터의 포맷, 모드, 프로토콜을 정하고 공유하기
- 데이터 공유하기

이런 절차를 위해 WebRTC는 API를 제공한다.

클라이언트 들이 서로를 발견하고, 네트워크 정보를 공유하고 포맷과 데이터를 주고받기 위해, WebRTC는 signaling 이라는 메카니즘을 이용한다.

## Signaling

시그널링은 네트워크 발견, 세션 생성, 세션 관리, 미디어 메타데이터를 교환하는 절차다.

시그널링은 통신을 시작하기 전에 서로를 인지해야 하기 때문에 필수로 해야 한다.

그러나, WebRTC는 시그널링에 대한 표준적인 구현이 없고, 오로지 개발자가 구현하도록 되어 있다.

## Signaling Server 만들기

시그널링 서버를 만들기 위해서 웹소켓 서버를 만들어 볼 것이다.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
    <version>2.4.0</version>
</dependency>
```

```java
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/socket")
          .setAllowedOrigins("*");
    }
}
```

## 메세지 핸들러 만들기

The next step is to create a message handler to process the WebSocket messages that we'll receive from multiple clients.

**This is essential to aid the exchange of metadata between the different clients to establish a direct WebRTC connection**.

Here, to keep things simple, when we receive the message from a client, we will send it to all other clients except to itself.

To do this, we can **extend *TextWebSocketHandler* from the Spring WebSocket library** and override both the *handleTextMessage* and *afterConnectionEstablished* methods:

```java
@Component
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession>sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
      throws InterruptedException, IOException {
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }
}
```

As we can see in the *afterConnectionEstablished* method, we add the received session to a list of sessions so that we can keep track of all the clients.

And when we receive a message from any of the clients, as can be seen in the *handleTextMessage,* we iterate over all the client sessions in the list and send the message to all other clients except the sender by comparing the session id of the sender and the sessions in the list.

## 메타데이터 교환하기

P2P 연결을 하는 클라이언트는 매우 다양하다.

안드로이드 유저가 크롬을 이용해서 Mac 운영체제에서 돌아가는 firefox에 연결할 수 있다.

이런 이유로, 이런 기기들의 미디어 전송량 서로 다르다.

그래서 두 피어간의 미디어 타입과 통신에 사용할 코덱을 정하는것이 중요하다.

WebRTC는  클라이언트 사이의 메타데이터를 정하기 위해 SDP(Session Description Protocol)를 이용한다.

To achieve this, the initiating peer creates an offer that must be set as a remote descriptor by the other peer. In addition, the other peer then generates an answer that is accepted as a remote descriptor by the initiating peer.

The connection is established when this process is complete.

## NAT 이슈

방화벽과 NAT(Network Address Traversal) 장치로 디바이스들이 인터넷에 접속한다.

NAT은 로컬 네트워크 안에서 사용할 목적으로 IP 주소를 할당하는데, 이 IP 주소는 로컬 네트워크 밖에서 접근이 불가능하다.

공인 IP 없이는 peer를 연결하기가 어렵다.

이 이슈를 해결하기 위해서는 WebRTC는 다음의 2가지 메카니즘을 이용한다.

## STUN 이용

STUN은 위의 문제를 가장 간단하게 해결한다.

피어에게 네트워크 정보를 공유하기 전에 먼저 SUTN 서버에게 보낼 요청을 만든다.

STUN 서버의 책임은 이 요청을 받은 IP 주소를 반환하는 것이다.

그러므로, STUN 서버에 질의함으로써 공인 IP 주소를 알아낼 수 있다.

그 다음 이 IP와 포트 정보를 연결할 클라이언트에게 공유하는 것이다.

연결할 상대측 클라이언트 또한 같은 절차를 밟아서 공인 IP 주소를 공유한다.

STUN 서버를 사용하기 위해서는 다음과 같이 해주기만 하면 된다.

(구글의 STUN 서버 사용함)

```jsx
var configuration = {
    "iceServers" : [ {
        "url" : "stun:stun2.1.google.com:19302"
    } ]
};
```

## TURN 이용

위와 반대로, TURN은 WebRTC가 P2P 연결을 맺지 못했을 때 대안으로 사용되는 메카니즘 중 하나다.

TURN 서버의 역할은 피어 간의 데이터를 중간에서 재전송을 해준다.

만약 클라이언트가 방화벽이나 프록시에 의해 가려져있어서 연결을 할 수 없다면 TURN 서버가 사용된다

하지만 TURN 서버는 사실상 P2P 연결이 아니고, 중간서버가 존재하게 되는 것이다.

TURN 서버는 P2P 연결을 맺지 못했을 때 최후의 보루로 사용해야 한다.

STURN 서버와 비슷하게, TURN 서버 URL을 제공하는 예제다.
