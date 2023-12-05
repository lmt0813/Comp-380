from sys import argv
import smtplib

def main():
    receiver = argv[1]
    subject = argv[2]
    body = argv[3]
    send(receiver=receiver, subject=subject,body=body)
# end main method
    
# end send with arguments method

def send(receiver, subject, body):
    sender = "kazjoey37@gmail.com"
    password = 'ubsy dsqs atrp hnyj'
    message = f"""From: {sender}
    To: {receiver}
    Subject: {subject}\n
    {body}
    """
    try:
        server = smtplib.SMTP("smtp.gmail.com", 587)
        server.starttls()
        server.login(sender,password)
        server.sendmail(sender, receiver, message)
        print("Email has been sent")
        
    except smtplib.SMTPAuthenticationError:
        print("unable to sign in")
        
# end send method

#declare main method for exceution
main()
    
