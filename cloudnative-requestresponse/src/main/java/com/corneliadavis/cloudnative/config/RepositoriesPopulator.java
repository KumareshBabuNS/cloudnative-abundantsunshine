package com.corneliadavis.cloudnative.config;

import com.corneliadavis.cloudnative.connections.Connection;
import com.corneliadavis.cloudnative.connections.ConnectionsController;
import com.corneliadavis.cloudnative.connections.User;
import com.corneliadavis.cloudnative.posts.Post;
import com.corneliadavis.cloudnative.posts.PostsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by corneliadavis on 9/4/17.
 */
@Component
public class RepositoriesPopulator implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(RepositoriesPopulator.class);
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Loading sample data");
        populate();
    }

    private void populate() {
        User user1, user2, user3;
        Post post1, post2, post3, post4;
        Connection connection1, connection2, connection3;
        ConnectionsController connectionsWriteController = applicationContext.getBean(ConnectionsController.class);
        PostsController postsWriteController = applicationContext.getBean(PostsController.class);

        user1 = new User("Cornelia", "cdavisafc");
        connectionsWriteController.newUser(user1,null);
        user2 = new User("Max", "madmax");
        connectionsWriteController.newUser(user2,null);
        user3 = new User( "Glen", "gmaxdavis");
        connectionsWriteController.newUser(user3,null);

        post1 = new Post(2L, "Max Title", "The body of the post");
        postsWriteController.newPost(post1, null);
        post2 = new Post(1L, "Cornelia Title", "The body of the post");
        postsWriteController.newPost(post2, null);
        post3 = new Post(1L, "Cornelia Title2", "The body of the post");
        postsWriteController.newPost(post3, null);
        post4 = new Post(3L, "Glen Title", "The body of the post");
        postsWriteController.newPost(post4, null);

        connection1 = new Connection(2L, 1L);
        connectionsWriteController.newConnection(connection1, null);
        connection2 = new Connection(1L, 2L);
        connectionsWriteController.newConnection(connection2, null);
        connection3 = new Connection(1L, 3L);
        connectionsWriteController.newConnection(connection3, null);

    }

}
