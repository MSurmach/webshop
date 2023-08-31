db.createUser(
    {
        user: "mongoUser",
        pwd: "strongpassword",
        roles: [
            {
                role: "readWrite",
                db: "productqueryDB"
            }
        ]
    }
);